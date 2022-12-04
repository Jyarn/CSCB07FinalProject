package Administrator;

import ddb.*;

public class AddCourse {

    public static void addCourse(Course course) {
        //need to get count of the number of courses add to the ddb, don't make node names as the course code
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        ddb.cd(course.courseCode);
        ddb.write(course);

        int count = 1;
        ddb.cd("sessions");
        if(course.offeringSessions.isEmpty() == true) ddb.write("none");
        else {
            for(String s: course.offeringSessions) { //add throws for invalid sessions?
                ddb.cd("session " + count);
                ddb.write(s);
                ddb.cd("..");
                count++;
            }
        }

        ddb.cd("..");
        ddb.cd("prerequisites");
        if(course.prerequisites.isEmpty() == true) ddb.write("none");
        else {
            count = 1;
            for(String s: course.prerequisites) {
                ddb.cd("prerequisite " + count);
                ddb.write(s);
                ddb.cd("..");
                count++;
            }
        }

    }
}