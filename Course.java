public class Course {
    String[] prerequisite;
    String[] sessions;

    public Course (String[] prerequisites, String[] sessions) {
        this.prerequisite = prerequisites;
        this.sessions = sessions;
    }
}