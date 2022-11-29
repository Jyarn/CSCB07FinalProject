package Administrator;

import java.util.HashSet;

public class AdminCourseManager {

    public static AdminCourseManager courseList;
    protected HashSet<Course> allCourses;
    String lastAction; //There probably is a better way to send this information around but I don't know how. Maybe through view binding or accessing the parent :\
    String debugText;

    private AdminCourseManager(){
        allCourses = new HashSet<Course>();
        //updateCourses();
        this.lastAction = "No recent action";
        this.debugText = "Debug";
    }

    public void updateCourses(){
        allCourses = ReadCourse.readAllCourses();
    }

    public static AdminCourseManager getInstance(){
        if(courseList == null)
            courseList = new AdminCourseManager();
        return courseList;
    }

    public void addCourse(Course newCourse){
        this.allCourses.add(newCourse);
    }

    public void removeCourse(Course defunctCourse){

        this.allCourses.remove(defunctCourse);
    }

    public int courseCount(){
        return allCourses.size();
    }

    public void setLastAction(String action){
        this.lastAction = action;
    }

    public void setDebugText(String text){
        this.debugText = text;
    }

    public void clear(){
        //just in case
        courseList = null;
        allCourses = null;
        //would cause memory leak if C, relying on the garbage collector here
    }
}
