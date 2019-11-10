package model;

public class Attendant {
    Student student;
    boolean status;

    public Attendant(Student s, boolean stt) {
        student = s;
        status = stt;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public boolean getStatus() {
        return status;
    }
}
