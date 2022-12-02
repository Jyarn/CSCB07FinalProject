import java.util.ArrayList;
import java.util.HashMap;

// TODO:
// add full course list
// listSessions
//
// genTree (Course matching)
// genTree (import dependencies)
// genTree (generating tree)
//
// generateTimetable

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
        return new ArrayList<String>();
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

    public HashMap<String, ArrayList<String>> generateTimetable (String[] courses) {
        HashMap<String, ArrayList<String>> ret = new HashMap<String, ArrayList<String>>();
        ret.put("Fall 2022", null);
        return ret;
    }
}
