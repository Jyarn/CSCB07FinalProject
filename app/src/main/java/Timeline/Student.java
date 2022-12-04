package Timeline;

import java.util.ArrayList;

public class Student extends Timeline{
    public Student (ArrayList<Course_Student> courses) {
        super.courses = courses;
    }

    public void addCourse (Course_Student add) {
        courses.add(add);
    }
}