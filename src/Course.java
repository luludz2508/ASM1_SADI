import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Course {


    private String Cid;
    private String Cname;
    private String Credit;

    public Course(String cid, String cname, String credit) {
        Cid = cid;
        Cname = cname;
        Credit = credit;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCredit() {
        return Credit;
    }

    public void setCredit(String credit) {
        Credit = credit;
    }


    @Override
    public String toString() {
        return "Course{" +
                "Cid='" + Cid + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Credit='" + Credit + '\'' +
                '}';
    }


}
