package Timeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// TODO:
// listSessions
//
// genTree (Course matching)
// genTree (import dependencies)
// genTree (generating tree)
//
// generateTimetable
//
// add full course list

public class Student {
    ArrayList<Course_Student> courses;

    public Student (ArrayList<Course_Student> courses) {
        this.courses = courses;
    }

    public void addCourse (Course_Student add) {
        courses.add(add);
    }

    public ArrayList<String> listSessions (HashMap<String, Course> parse) {
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

    public HashMap<String, Course> ddbPull () {
        // pull a set of all courses from the database
        HashMap<String, Course> ret = new HashMap<String, Course>();

        Course r = new Course(new String[]{}, new String[]{"Fall 2022","Winter 2023", "Summer 2023"});
        ret.put("CSCA08", r);

        r = new Course(new String[]{"CSCA08"}, new String[]{"Winter 2023", "Summer 2023"});
        ret.put("CSCA48", r);

        r = new Course(new String[]{"CSCA08"}, new String[]{"Winter 2023"});
        ret.put("CSCA67", r);

        r = new Course(new String[]{}, new String[]{"Summer 2023", "Winter 2023"});
        ret.put("MATA22", r);

        r = new Course(new String[]{}, new String[]{"Fall 2022","Winter 2023"});
        ret.put("MATA31", r);

        r = new Course(new String[]{"MATA31", "CSCA67"}, new String[]{"Winter 2023", "Summer 2023"});
        ret.put("MATA37", r);

        r = new Course(new String[]{"CSCA48"}, new String[]{"Fall 2022", "Summer 2023"});
        ret.put("CSCB07", r);

        r = new Course(new String[]{"CSCA48"}, new String[]{"Winter 2023", "Summer 2023"});
        ret.put("CSCB09", r);

        r = new Course(new String[]{"CSCA48", "CSCA67"}, new String[]{"Fall 2022", "Winter 2023", "Summer 2023"});
        ret.put("CSCB36", r);

        r = new Course(new String[]{"CSCA48"}, new String[]{"Fall 2022", "Winter 2023", "Summer 2023"});
        ret.put("CSCB58", r);

        r = new Course(new String[]{"CSCA36"}, new String[]{"Fall 2022", "Winter 2023", "Summer 2023"});
        ret.put("CSCB63", r);

        r = new Course(new String[]{"MATA22"}, new String[]{"Fall 2022", "Winter 2023", "Summer 2023"});
        ret.put("MATB24", r);

        r = new Course(new String[]{"MATA37"}, new String[]{"Fall 2022", "Winter 2023", "Summer 2023"});
        ret.put("STAB52", r);

        return ret;
    }

    public Tree genTree (ArrayList<String> reqCourses, HashMap<String, Course> courseRef) {
        // public facing wrapper genTree


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

        // loop through all courses then append to HEAD, subtree generation is handled by genTree(Tree r)
        Tree head = new Tree("__HEAD__");

        while (mrgdCrrss.size() != 0) {
            genTree(head, mrgdCrrss.get(0), courseRef);
            mrgdCrrss.remove(0);
        }

        return head;
    }

    private Tree genTree (Tree r, String imprt, HashMap<String, Course> courseRef) {
        //                            Tree r -    Tree HEAD
        //                      String imprt -    Course Code of course to be added to tree
        // HashMap<String, Course> courseRef -    List of all course + deps in database

        // match course to their prerequisites organized in a tree
        //
        // only include courses that are necessary, so if a prerequisite course has been
        // completed it will not be added
        //
        // duplicates nodes are not a bug but a feature

        Tree pass = new Tree(imprt);
        r.addNode(pass);

        boolean hasBeenCompleted = false;

        for (String i : courseRef.get(imprt).prerequisite) {
            for (Course_Student j : courses) {
                if (j.courseCode.equals(i) && j.completed) {
                    hasBeenCompleted = true;
                    break;
                }
            }

            if (!hasBeenCompleted) {
                genTree(pass, i, courseRef);
            }

            hasBeenCompleted = false;
        }

        return r;
    }

    public int importPrereqs (HashMap<String, Course> courseRef, ArrayList<String> reqCourses) {
        int r = 0; // number of courses that have been imported
        boolean hasBeenCompleted = false;
        ArrayList<String> m = new ArrayList<String>();

        for (String i : reqCourses) {
            for (String j : courseRef.get(i).prerequisite) {
                for (Course_Student k : courses) {
                    if (k.courseCode.equals(j) && k.completed) {
                        hasBeenCompleted = true;
                        break;
                    }
                }

                if (!hasBeenCompleted && !reqCourses.contains(j)) {
                    m.add(j);
                    r += 1;
                }

                hasBeenCompleted = false;
            }
        }

        for (String i : m) {
            reqCourses.add(i);
        }

        return r;
    }

    public ArrayList<String> mergeCourses (ArrayList<String> reqCourses) {
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

        HashMap<String, Course> rawData = ddbPull();
        ArrayList<String> sessions = listSessions(rawData);
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

    public boolean validatePlacement (ArrayList<String> sessions, HashMap<String, Course> courseRef,
    HashMap<String, ArrayList<String>> v, String reqCourse, String p) {

        // session - list of all sessions
        // courseRef - all courses + their prerequisites/session dates
        // v - the thing being validated
        // reqCourse - the course we wish to place in v
        // p - the session date we wish to place reqCourse in

        /*
            a placement for a requested course is considered valid if:
             i. it is placed in a spot before its prerequisites (to ensure we go through all possible combination
             we don't require each prerequisited to be in v)

             ii. there aren't more than $(courseLimit) course in a session
        */

        int courseLimit = 6;
        int pIndex = sessions.indexOf(p);

        if (v.get(p).size() == courseLimit) { return false; }

        for (int i = pIndex; i < sessions.size(); i++) {
            for (String preReq : courseRef.get(reqCourse).prerequisite) {
                String s = sessions.get(i);

                if (v.get(s).contains(preReq)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean RECURSE_genTimetable (ArrayList<String> session, HashMap<String, Course> courseRef,
    HashMap<String, ArrayList<String>> ret, ArrayList<String> reqCourses) {
        boolean success = false;

        // recurisve backtracker
        for (String course : reqCourses) {
            // find session
            for (String sess : courseRef.get(course).sessions) {
                if (validatePlacement(session, courseRef, ret, course, sess)) {
                    ret.get(sess).add(course);
                    reqCourses.remove(course);

                    if (RECURSE_genTimetable(session, courseRef, ret, reqCourses)) {
                        return true;
                    }

                    ret.get(sess).remove(course);
                    reqCourses.add(course);
                }
            }
        }

        if (reqCourses.size() == 0) {
            return true;
        }
        return false;
    }
}
