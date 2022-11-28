package Administrator;

public class Driver {
    public static void main () {

    /*
        //Test 1 / Example on how to add a course
        Course x = new Course("Software Design", "CSCB07");
        Course y = new Course("Introduction to Computer Science II", "CSCA48");
        x.addOfferingSession("Fall");
        x.addOfferingSession("Winter");
        x.addOfferingSession("Summer");
        x.addPrerequisite(y);
        AddCourse.addCourse(x);
        //AddCourse.addCourse(y);
        Log.i("Hi", "qqqqqqqqqqqqqqqqqqqqq");
     */

        /*
        HashSet<String> a = new HashSet<String>();
        a.add("CSCA08");
        EditCourse.editPrerequisites(a, "CSCA48");

         */

        //Course y = new Course("Introduction to Computer Science II QQQ", "CSCA48");
        //EditCourse.editCourse(y);

        /*
        Course a = ReadCourse.readCourse("CSCB07");
        Log.i("a", a.name);
        EditCourse.editCourseName("CSCB07", "hi");
        Course b = ReadCourse.readCourse("CSCB07");
        Log.i("b", b.name);
        //DeleteCourse.deleteCourse("CSCB07");


         */

    /*
        HashSet<Course> a = ReadCourse.readAllCourses();
        for(Course c: a) {
            //if(c.courseCode == null) Log.i("e", "bruh");
            //else Log.i("f", c.courseCode);
            //if(c.courseCode == "CSCB07") Log.i("g", "bruh");
            Log.i("d", c.name);
            Log.i("d", c.courseCode);
        }

     */
       /* ddb.cd("CSCB07");
        ddb.cd("sessions");
        HashMap<String, String> sessions = (HashMap<String, String>) ddb.read();
        for(String i: sessions.keySet()) {
            Log.i("d", i);
        }
        /*
        for (String i: coursesHM.keySet()) {
            ddb.cd("i");
            ddb.cd("courseCode");
            String code = (String) ddb.read();
            Log.i("c", code);
        }

         */

        /*
                Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "Courses");
        ddb.cd("CSCB07");

        //get course code
        ddb.cd("courseCode");
        String courseCode = (String) ddb.read();
        ddb.cd("..");
        Log.i("d", courseCode);

        //get course name
        ddb.cd("name");
        String name = (String) ddb.read();
        ddb.cd("..");
        Log.i("d", name);

        //get offering sessions
        ddb.cd("sessions");
        HashSet<String> offeringSessions = new HashSet<String>();
        HashMap<String, String> sessions = (HashMap<String, String>) ddb.read();
        for(String s: sessions.keySet()) {
            ddb.cd(s);
            String x = (String) ddb.read();
            offeringSessions.add(x);
            ddb.cd("..");
            Log.i("d", x);
        }
        ddb.cd("..");
         */

    }
}
