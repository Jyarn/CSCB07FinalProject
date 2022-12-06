package Administrator;

import ddb.Database;

public class AddCourse {

    public static void addCourse(Course course) {
        Course.AllCourses.put(course.courseCode, course);

    }
}
