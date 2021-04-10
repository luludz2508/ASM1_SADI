public class StudentEnrollment {
    Student student;
    Course course;
    String semester;

    public StudentEnrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return student.getSid() +
                "," + student.getSname() +
                "," + student.getSbd() +
                "," + course.getCid() +
                "," + course.getCname() +
                "," + course.getCredit() +
                "," + semester + "\n" ;
    }
}
