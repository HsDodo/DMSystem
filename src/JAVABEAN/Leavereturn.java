package JAVABEAN;

import java.util.Date;

public class Leavereturn {
    private String studentname;
    private String studentid;
    private String dormitoryid;
    private Date   leavetime;
    private Date   returntime;

    public String getDormitoryid() {
        return dormitoryid;
    }

    public String getStudentid(){ return studentid;}

    public void setStudentid(String studentid){ this.studentid=studentid;}

    public void setDormitoryid(String dormitoryid) {
        this.dormitoryid = dormitoryid;
    }

    public Date getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Date leavetime) {
        this.leavetime = leavetime;
    }

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}
