package Administrator;

import java.util.HashSet;

public class AdminCourseManager {

    public static AdminCourseManager courseManager;
    //There probably is a better way to send this information around but I don't know how. Maybe through view binding or accessing the parent :\
    protected HashSet<Course> allCourses;
    String lastAction; String debugText;

    private AdminCourseManager(){
        allCourses = new HashSet<Course>();
        updateCourses();
        this.lastAction = "No recent action";
        this.debugText = "Debug";
    }

    public void updateCourses(){
        allCourses = ReadCourse.readAllCourses();
    }

    public static AdminCourseManager getInstance(){
        if(courseManager == null)
            courseManager = new AdminCourseManager();
        return courseManager;
    }

    public void addCourse(Course newCourse){
        this.allCourses.add(newCourse);
        AddCourse.addCourse(newCourse);
    }

    public HashSet<Course> getCopyOfCourses(){
        HashSet<Course> newCopy = new HashSet<Course>();
        newCopy.addAll(allCourses);
        return newCopy;
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
        courseManager = null;
        allCourses = null;
        //would cause memory leak if C, relying on the garbage collector here
    }
}
