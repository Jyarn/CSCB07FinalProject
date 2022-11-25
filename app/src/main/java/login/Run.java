package login;

import ddb.Database;

public class Run {
    public static void run () {
        Student s = new Student ("https://chungus-1a829-default-rtdb.firebaseio.com/", "ddddd");
        s.register("ppp");

        s.addCourse(new Course_Student("CSCB07", false));
        s.addCourse(new Course_Student("CSCA08", true));
        s.addCourse(new Course_Student("MATA37", true));
        s.addCourse(new Course_Student("CSCA48", true));
        s.addCourse(new Course_Student("MATA22", true));
        s.addCourse(new Course_Student("MATA31", true));
        s.addCourse(new Course_Student("STAB52", false));

        Student v = new Student("https://chungus-1a829-default-rtdb.firebaseio.com/", "slfkjsldkf");
        v.register("sfsdsf");
        v.addCourse(new Course_Student("MAMAMMAM", false));
        v.push();

        s.push();

        v.login("sfdsf");

        Student r = new Student ("https://chungus-1a829-default-rtdb.firebaseio.com/", "slfkjsldkf");
        boolean b = r.isRegistered();
        r.pull();

        Database d = new Database("https://chungus-1a829-default-rtdb.firebaseio.com/", "k,");
        d.read();
    }
}
