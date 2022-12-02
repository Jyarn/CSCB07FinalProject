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

    public Tree genTree () {
        // match course to their prerequisites organized in a treee
        // courses with the same dependencies will be linked together
        //
        // only includes courses that are necessary, so if a prerequisite course has been
        // completed it will not be completed
        return new Tree();
    }

    public HashMap<String, ArrayList<String>> generateTimetable (ArrayList<String> courses) {
        // prepare for recurison
        return null;
    }

    private HashMap<String, ArrayList<String>> RECURSE_genTimetable (ArrayList<String> courses) {
        return null;
    }
}
