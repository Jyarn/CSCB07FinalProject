package Timeline;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static void main (String[] args) {
        Student std = new Student(new ArrayList<Course_Student>());

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Courses: ");

        String a = in.nextLine();
        String[] proc = a.split(" ");

        boolean[] match = new boolean[]{false, true};

        while (proc.length == 2) {
            std.addCourse(new Course_Student(match[Integer.parseInt(proc[0])], proc[1]));
            a = in.nextLine();
            proc = a.split(" ");
        }

        System.out.println(std.generateTimetable(new ArrayList<String>()));
        in.close();

        std = new Student(new ArrayList<Course_Student>());
        ArrayList<String> o = new ArrayList<String>();
        o.add("MATB24");
        o.add("CSCB07");
        System.out.println(std.generateTimetable(o));
    }
}