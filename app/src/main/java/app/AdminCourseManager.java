package app;

import java.util.HashSet;

public class AdminCourseManager {

    public static AdminCourseManager courseList;
    protected HashSet<AdminCourse> allCourses;
    String lastAction;

    private AdminCourseManager(){
        allCourses = new HashSet<AdminCourse>();
        this.lastAction = "No new actions";
    }

    public static AdminCourseManager getInstance(){
        if(courseList == null)
            courseList = new AdminCourseManager();
        return courseList;
    }

    public void addCourse(AdminCourse newCourse){
        this.allCourses.add(newCourse);
    }

    public void removeCourse(AdminCourse defunctCourse){
        this.allCourses.remove(defunctCourse);
    }

    public int courseCount(){
        return allCourses.size();
    }

    public void setAction(String action){
        this.lastAction = action;
    }

    public void clear(){
        //just in case
        courseList = null;
        allCourses = null;
        //would cause memory leak if C, relying on the garbage collector here
    }
}
