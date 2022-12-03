package Administrator;

import java.util.HashSet;
import java.util.Objects;

public class Course {
    String name;
    String courseCode;
    HashSet<String> offeringSessions;
    HashSet<String> prerequisites;

    public String getName() {
        return name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Course(String name, String courseCode) {
        this.name = name;
        this.courseCode = courseCode;
        offeringSessions = new HashSet<String>();
        prerequisites = new HashSet<String>();
    }

    public Course(String name, String courseCode, HashSet<String> offeringSessions, HashSet<String> prerequisites) {
        this.name = name;
        this.courseCode = courseCode;
        this.offeringSessions = offeringSessions;
        this.prerequisites = prerequisites;
    }

    public void addOfferingSession(String session) {
        if (session == "Winter" || session == "Summer" || session == "Fall")
            offeringSessions.add(session);
        //else: throw invalidSessionException
    }

    public void removeOfferingSession(String session) {
        offeringSessions.remove(session);
    }

    public boolean offeringSessionExists(String session) {
        return offeringSessions.contains(session);
    }

    public void addPrerequisite(Course course) {
        prerequisites.add(course.courseCode);
    }

    public void removePrerequisite(Course course) {
        prerequisites.remove(course.courseCode);
    }

    public boolean prerequisiteExists(Course course) {
        return prerequisites.contains(course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courseCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Course)) return false;
        Course other = (Course) obj;
        return this.name == other.name && this.courseCode == other.courseCode;
    }

    @Override
    public String toString() {
        return "Name: " + name + " - Course Code: " + courseCode;
    }

    // REQUIRED BY TIMELINE
    public HashSet<String> getSessions () {
        return offeringSessions;
    }

    public HashSet<String> getPrerequisiteList () {
        return prerequisites;
    }
}

