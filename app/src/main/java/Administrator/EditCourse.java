package Administrator;

import java.util.HashSet;

import ddb.Database;

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
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        ddb.cd(courseCode);
        ddb.cd("name");
        ddb.write(newName);
    }

    public static void editSessions(HashSet<String> sessions, String courseCode) {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        ddb.cd(courseCode);
        ddb.cd("sessions");
        ddb.remove();

        int count = 1;
        if(sessions.isEmpty() == true) ddb.write("none");
        else {
            for (String s : sessions) { //add a throws for invalid session input?
                ddb.cd("session " + count);
                ddb.write(s);
                ddb.cd("..");
                count++;
            }
        }
    }

    public static void editPrerequisites(HashSet<String> prerequisites, String courseCode) {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        ddb.cd(courseCode);
        ddb.cd("prerequisites");
        ddb.remove();

        int count = 1;
        if(prerequisites.isEmpty() == true) ddb.write("none");
        else {
            for (String p : prerequisites) {
                ddb.cd("prerequisite " + count);
                ddb.write(p);
                ddb.cd("..");
                count++;
            }
        }
    }
}
