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

        Course r = new Course(new String[]{"MATA22"}, new String[]{"Fall 2022", "Fall 2023"});
        ret.put("CSCB07", r);

        r = new Course(new String[]{"MATA22"}, new String[]{"Fall 2022", "Fall 2023"});
        ret.put("MATA22", r);
        return ret;
    }

    public Tree genTree (ArrayList<String> reqCourses) {
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
        for (String i : reqCourses) {
            if (!mrgdCrrss.contains(i)) {
                mrgdCrrss.add(i);
            }
        }

        // loop through all courses then append to HEAD, subtree generation is handled by genTree(Tree r)
        Tree head = new Tree("__HEAD__");

        while (mrgdCrrss.size() != 0) {
            Tree appd = genTree(head, mrgdCrrss.get(0));
            mrgdCrrss.remove(0);

            head.addNode(appd);
        }

        return head;
    }

    private Tree genTree (Tree r, String imprt) {
        // match course to their prerequisites organized in a tree
        //
        // only include courses that are necessary, so if a prerequisite course has been
        // completed it will not be added
        //
        // duplicates nodes are not a bug but a feature
        return null;
    }

    public HashMap<String, ArrayList<String>> generateTimetable (ArrayList<String> courses) {
        // prepare for recurison
        // I refer to dependencies and prerequisites as the same thing
        HashMap<String, Course> rawData = ddbPull();
        genTree(courses);

        return null;
    }

    private HashMap<String, ArrayList<String>> RECURSE_genTimetable (ArrayList<String> courses) {
        return null;
    }
}
