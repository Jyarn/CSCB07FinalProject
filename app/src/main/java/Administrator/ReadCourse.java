package Administrator;

import android.util.Log;

import ddb.Database;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ReadCourse {

    public static Course readCourse(String i) {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        ddb.cd(i);

        //get course code
        ddb.cd("courseCode");
        String courseCode = (String) ddb.read();
        ddb.cd("..");

        //get course name
        ddb.cd("name");
        String name = (String) ddb.read();
        ddb.cd("..");


        //get offering sessions
        HashSet<String> offeringSessions = new HashSet<String>();
        ddb.cd("sessions");
        if(ddb.read() instanceof HashMap) {
            HashMap<String, String> sessions = (HashMap<String, String>) ddb.read();
            for(String s: sessions.keySet()) {
                ddb.cd(s);
                offeringSessions.add((String) ddb.read());
                ddb.cd("..");
            }
        }

        ddb.cd("..");

        //get prerequisites
        HashSet<String> prerequisites = new HashSet<String>();
        ddb.cd("prerequisites");
        if(ddb.read() instanceof HashMap) {
            HashMap<String, String> pMap = (HashMap<String, String>) ddb.read();
            for(String p: pMap.keySet()) {
                ddb.cd(p);
                prerequisites.add((String) ddb.read());
                ddb.cd("..");
            }
        }

        return new Course(name, courseCode, offeringSessions, prerequisites);
    }
    public static HashSet<Course> readAllCourses() {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        HashMap<String, String> coursesHM = (HashMap<String, String>) ddb.read();

        HashSet<Course> coursesList = new HashSet<Course>();
        for (String i: coursesHM.keySet()) {
            coursesList.add(readCourse(i));
        }

        return coursesList;
    }
}
