package Administrator;

import ddb.Database;

public class DeleteCourse {

    public static boolean deleteCourse(String courseCode) {
        Course.AllCourses.remove(courseCode);
        return true; //always returns true?
    }

    public static boolean deleteCourse(Course course) {
        deleteCourse(course.courseCode);
        return true; //always returns true?
    }
}
