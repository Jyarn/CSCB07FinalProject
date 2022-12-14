package Timeline;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;

import Administrator.Course;

public abstract class Timeline {
    ArrayList<Course_Student> courses;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private ArrayList<String> listSessions (HashMap<String, CRS> parse) {
        // return a sorted list of all sessions
        ArrayList<String> r = new ArrayList<String>();

        for (String key : parse.keySet()) {
            for (String c : parse.get(key).sessions) {
                if (!r.contains(c)) {
                    r.add(c);
                }
            }
        }

        r.sort(new SessionSorter());
        return r;
    }

    private HashMap<String, CRS> ddbPull () {
        // pull a set of all courses from the database
        HashMap<String, CRS> ret = new HashMap<String, CRS>();

        CRS r = new CRS(new String[]{}, new String[]{"Fall 2022","Winter 2023", "Summer 2023"});
        ret.put("CSCA08", r);

        r = new CRS(new String[]{"CSCA08"}, new String[]{"Winter 2023", "Summer 2023"});
        ret.put("CSCA48", r);

        r = new CRS(new String[]{"CSCA08"}, new String[]{"Winter 2023"});
        ret.put("CSCA67", r);

        r = new CRS(new String[]{}, new String[]{"Summer 2023", "Winter 2023"});
        ret.put("MATA22", r);

        r = new CRS(new String[]{}, new String[]{"Fall 2022","Winter 2023"});
        ret.put("MATA31", r);

        r = new CRS(new String[]{"MATA31", "CSCA67"}, new String[]{"Winter 2023", "Summer 2023"});
        ret.put("MATA37", r);

        r = new CRS(new String[]{"CSCA48"}, new String[]{"Fall 2022", "Summer 2023"});
        ret.put("CSCB07", r);

        r = new CRS(new String[]{"CSCA48"}, new String[]{"Winter 2023", "Summer 2023"});
        ret.put("CSCB09", r);

        r = new CRS(new String[]{"CSCA48", "CSCA67"}, new String[]{"Fall 2022", "Winter 2023", "Summer 2023"});
        ret.put("CSCB36", r);

        r = new CRS(new String[]{"CSCA48"}, new String[]{"Fall 2022", "Winter 2023", "Summer 2023"});
        ret.put("CSCB58", r);

        r = new CRS(new String[]{"CSCA36"}, new String[]{"Fall 2022", "Winter 2023", "Summer 2023"});
        ret.put("CSCB63", r);

        r = new CRS(new String[]{"MATA22"}, new String[]{"Fall 2022", "Winter 2023", "Summer 2023"});
        ret.put("MATB24", r);

        r = new CRS(new String[]{"MATA37"}, new String[]{"Fall 2022", "Winter 2023", "Summer 2023"});
        ret.put("STAB52", r);

        return ret;
    }

    private boolean hasBeenCompleted (String course) {
        for (Course_Student i : courses) {
            if (i.completed && i.courseCode.equals(course)) {
                return true;
            }
        }

        return false;
    }

    private int importPrereqs (HashMap<String, CRS> courseRef, ArrayList<String> reqCourses) {
        int r = 0; // number of courses that have been imported
        ArrayList<String> m = new ArrayList<String>();

        for (String i : reqCourses) {
            for (String j : courseRef.get(i).prerequisite) {
                if (!hasBeenCompleted(j) && !reqCourses.contains(j)) {
                    m.add(j);
                    r += 1;
                }
            }
        }

        for (String i : m) {
            reqCourses.add(i);
        }

        return r;
    }

    private ArrayList<String> mergeCourses (ArrayList<String> reqCourses) {
        // merge courses no duplicates
        ArrayList<String> mrgdCrrss = new ArrayList<String>(); // merged courses

        //merge stored courses that have not been completed
        for (Course_Student i : courses) {
            if (!i.completed && !mrgdCrrss.contains(i.courseCode)) {
                mrgdCrrss.add(i.courseCode);
            }
        }

        // merge courses as requested by generateTimetable
        if (reqCourses != null) {
            for (String i : reqCourses) {
                if (!mrgdCrrss.contains(i)) {
                    mrgdCrrss.add(i);
                }
            }
        }

        return mrgdCrrss;
    }

    public HashMap<String, ArrayList<String>> generateTimetable (ArrayList<String> reqCourses) {
        // prepare for recurison
        // I refer to dependencies and prerequisites as the same thing

        HashMap<String, CRS> rawData = ddbPull();
        ArrayList<String> sessions = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sessions = listSessions(rawData);
        }
        ArrayList<String> mergedCourses = mergeCourses(reqCourses);

        while (importPrereqs(rawData, mergedCourses) != 0) { }

        HashMap<String, ArrayList<String>> ret = new HashMap<String, ArrayList<String>>();

        // initalize return
        for (String i : sessions) {
            ret.put(i, new ArrayList<String>());
        }

        RECURSE_genTimetable(sessions, rawData, ret, mergedCourses);
        return ret;
    }

    private boolean validate (ArrayList<String> sessions, HashMap<String, CRS> courseRef,
                              HashMap<String, ArrayList<String>> v, String courseReq, int i) {

        // sessions - list of all sessions
        // courseRef - all courses + their prerequisites/session dates
        // v - the thing being validated
        // courseReq - the course being validated
        // i - index of the session courseReq is in (relative to sessions)

        /*
            a timetable is considered valid if:
             i. if all course are placed in a spot before its prerequisites (to ensure we go through all possible combination
             we don't require each prerequisited to be in v)
             ii. there aren't more than $(courseLimit) course in a session (checked in validatePlacement)
        */

        // create a local copy of i so we don't modify it directly
        for (int index = i; index < sessions.size(); index++) {
            for (String preReq : courseRef.get(courseReq).prerequisite) {
                ArrayList<String> sess = v.get(sessions.get(index));
                if (hasBeenCompleted(preReq)) { continue; }
                if (sess.contains(preReq)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean validatePlacement (HashMap<String, ArrayList<String>> ret, ArrayList<String> sessions,
                                       HashMap<String, CRS> courseRef) {

        // wrapper class for validate to validate all of the courses in ret
        // see validate for conditions

        int courseLimit = 6;
        for (int i = 0; i < sessions.size(); i++) {
            if (ret.get(sessions.get(i)).size() > courseLimit) { return false; }
            for (String c : ret.get(sessions.get(i))) {
                if (!validate(sessions, courseRef, ret, c, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean RECURSE_genTimetable (ArrayList<String> session, HashMap<String, CRS> courseRef,
                                          HashMap<String, ArrayList<String>> ret, ArrayList<String> reqCourses) {
        // recursive backtracker
        ArrayList<String> s = new ArrayList<String>();
        for (String i : reqCourses) {
            s.add(i);
        }

        for (String course : s) {
            // find session
            for (String sess : session) {
                if (!courseRef.get(course).sessions.contains(sess)) { continue; }
                ret.get(sess).add(course);
                reqCourses.remove(course);

                if (validatePlacement(ret, session, courseRef)) {
                    if (RECURSE_genTimetable(session, courseRef, ret, reqCourses)) {
                        return true;
                    }
                }

                ret.get(sess).remove(course);
                reqCourses.add(course);
            }
        }

        if (reqCourses.size() == 0) {
            return true;
        }

        return false;
    }
}