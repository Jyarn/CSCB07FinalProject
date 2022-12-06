package Administrator;

import java.util.HashSet;

import ddb.Database;

//import ddb.Database;

public class EditCourse {

    public static void replaceCourse(Course replacement, String courseToReplace) {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        DeleteCourse.deleteCourse(courseToReplace);
        AddCourse.addCourse(replacement);
    }

    //THIS DOES NOT WORK IF YOU CHANGE THE COURSE CODE
    public static void editCourse(Course course) {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        DeleteCourse.deleteCourse(course.courseCode);
        AddCourse.addCourse(course);
    }

    public static void editCourseName(String courseCode, String newName) {
        Course.AllCourses.get(courseCode).name = newName;
    }

    public static void editSessions(HashSet<String> sessions, String courseCode) {
        Course.AllCourses.get(courseCode).offeringSessions = sessions;
    }

    public static void editPrerequisites(HashSet<String> prerequisites, String courseCode) {
        Course.AllCourses.get(courseCode).prerequisites = prerequisites;
    }
}
