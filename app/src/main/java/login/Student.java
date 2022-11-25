package login;

import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import ddb.Database;

public class Student extends User {
    private ArrayList<Course_Student> courses;

    public Student (String url, String username) {
        super(new Database(url, "Students/" + username), "Student");
        this.courses = new ArrayList<Course_Student>();
        this.username = username;
    }

    public void register (String password) {
        super.register(password);
        push();
    }

    public boolean push () {
        ddbLoc.cd("courses");
        boolean ret = ddbLoc.write(courses);

        ddbLoc.cd("..");
        return ret;
    }

    public void addCourse (Course_Student course) {
        courses.add(course);
    }

    public boolean pull () {
        try {
            ddbLoc.cd("courses");
            hashCast((ArrayList< HashMap<String, Object> >) ddbLoc.read());

            ddbLoc.cd("..");
            return this.courses != null;
        }

        catch (ClassCastException err) {
            Log.e("login", err.getMessage());
            return false;
        }
    }

    private void hashCast (ArrayList<HashMap<String, Object>> in) throws ClassCastException {
        // cast ArrayList<HashMap<String, Object>> -> Course_Student
        this.courses = new ArrayList<Course_Student>();

        for (HashMap<String, Object> i : in) {
            String ddbRef = (String)i.get("ddbref");
            boolean completed = (boolean)i.get("completed");
            this.courses.add(new Course_Student(ddbRef, completed));
        }
    }
}
