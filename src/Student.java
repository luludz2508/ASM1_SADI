import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Student {

    private String Sid;
    private String Sname;
    private String Sbd;

    public Student(String sid, String sname, String sbd) {
        Sid = sid;
        Sname = sname;
        Sbd = sbd;
    }


    public Student(String sid) {
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSbd() {
        return Sbd;
    }

    public void setSbd(String sbd) {
        Sbd = sbd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Sid='" + Sid + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Sbd='" + Sbd + '\'' +
                '}';
    }
}
