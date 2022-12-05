package app;

public class TimelineModel {

    String session;
    String courses;

    public TimelineModel(String session, String courses) {
        this.session = session;
        this.courses = courses;
    }

    public String getSession() {
        return session;
    }

    public String getCourses() {
        return courses;
    }
}
