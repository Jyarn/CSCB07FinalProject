package Timeline;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public void main () {
        Student std = new Student(new ArrayList<Course_Student>());
        ArrayList<String> req = new ArrayList<String>();
        req.add("MATB02");
        std.generateTimetable(req);
    }
}