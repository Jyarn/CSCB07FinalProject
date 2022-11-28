package app;
import java.util.HashSet;

// ** this class is intended to be completely temporary **

public class AdminCourse {

    String code;
    String name;
    HashSet<String> sessions;

    public AdminCourse(String newCourseCode, String newCourseName, HashSet<String> newSessions){
        this.code = newCourseCode;
        this.name = newCourseName;
        this.sessions = newSessions;
    }

    public void setCourseCode(String newThing){
        this.code = newThing;
    }

    public void setName(String newThing){
        this.name = newThing;
    }

    public void addSession(String newSession){
        this.sessions.add(newSession);
    }

    public void removeSession(String defunctSession){
        this.sessions.remove(defunctSession);
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public HashSet<String> getSessions(){
        return sessions;
    }

    public int getSessionCount(){return sessions.size();}

    @Override
    public int hashCode(){
        return this.code.length()+(this.name.length()*5)+(sessions.size()*17);
    }
}
