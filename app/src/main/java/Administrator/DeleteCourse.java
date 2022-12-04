package Administrator;

import ddb.Database;

public class DeleteCourse {

    public static boolean deleteCourse(String courseCode) {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        ddb.cd(courseCode);
        ddb.remove();

        return true; //always returns true?
    }

    public static boolean deleteCourse(Course course) {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        ddb.cd(course.courseCode);
        ddb.remove();

        return true; //always returns true?
    }
}