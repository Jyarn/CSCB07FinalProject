package Timeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {
    public void Run () {
        Student std = new Student(new ArrayList<Course_Student>());
        ArrayList<String> req = new ArrayList<String>();
        req.add("MATB02");
        HashMap<String, ArrayList<String>> a = std.generateTimetable(req);
    }
}