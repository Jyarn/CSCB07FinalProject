package login;

public class Course_Student {
    String ddbRef; // location of course information relative to database root
    boolean completed;

    public Course_Student () {
        // default constructor for firebase compatability
    }

    public Course_Student (String ddbRef, boolean completed) {
        this.ddbRef = ddbRef;
        this.completed = completed;
    }


    // getters and setters for firebase
    public void setDDBRef (String ddbRef) {
        this.ddbRef = ddbRef;
    }

    public String getDDBref () {
        return this.ddbRef;
    }

    public void setCompleted (boolean completed) {
        this.completed = completed;
    }

    public boolean getCompleted () {
        return this.completed;
    }
}
