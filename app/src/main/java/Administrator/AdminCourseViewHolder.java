package Administrator;



public class AdminCourseViewHolder{
    String viewholderCourseName;
    String viewholderCourseCode;

    public AdminCourseViewHolder(String viewholderCourseName, String viewholderCourseCode) {
        this.viewholderCourseName = viewholderCourseName;
        this.viewholderCourseCode = viewholderCourseCode;
    }

    public String getViewholderCourseName() {
        return viewholderCourseName;
    }

    public void setViewholderCourseName(String viewholderCourseName) {
        this.viewholderCourseName = viewholderCourseName;
    }

    public String getViewholderCourseCode() {
        return viewholderCourseCode;
    }

    public void setViewholderCourseCode(String viewholderCourseCode) {
        this.viewholderCourseCode = viewholderCourseCode;
    }
}
