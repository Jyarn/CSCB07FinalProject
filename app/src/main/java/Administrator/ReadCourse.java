package Administrator;

import java.util.HashMap;
import java.util.HashSet;

import ddb.Database;

public class ReadCourse {

    public static Course readCourse(String i) {
        return Course.AllCourses.get(i);
    }
    public static HashSet<Course> readAllCourses() {
        HashSet<Course> coursesList = new HashSet<Course>();
        for (Course i: Course.listAllCourses().values()) {
            coursesList.add(i);
        }

        return coursesList;
    }
}
