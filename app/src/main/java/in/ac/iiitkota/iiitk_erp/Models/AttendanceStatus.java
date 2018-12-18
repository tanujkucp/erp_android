package in.ac.iiitkota.iiitk_erp.Models;

public class AttendanceStatus {
    public String subjectCode;
    public int present,absent,total,leave;
    public float percentage;

    public String t1,t2,t3;

    public AttendanceStatus(String s,int p,int t,int l){
        subjectCode = s;
        present = p;
        leave = l;
        total = t;
        percentage = (p*100)/t;
        System.out.println(percentage);

        t1 = "Subject Code: "+subjectCode;
        t2 = "Attended "+String.valueOf(present)+" out of "+String.valueOf(total)+" class with "
                +String.valueOf(leave)+" leave";
        t3 = "Currently "+String.valueOf(percentage)+"% Attendance excluding leave";
    }

    public AttendanceStatus(){ }

}
