package in.ac.iiitkota.iiitk_erp.Models;

public class TimeTable {
    private String fromTime, toTime;
    private String venue;
    private String subject;
    private Boolean holiday = false;
    private String holidayMessage;

    public TimeTable(){ }
    public TimeTable(String fromTime,String toTime,String subject,String venue){
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.subject = subject;
        this.venue = venue;
    }

    public TimeTable(Boolean holiday){
        if(holiday==true){
            this.holiday=holiday;
            holidayMessage = "Today is Holiday";
        }
    }

    public String getTime(){
        String time = "Time- "+fromTime+" to "+toTime;
        return time;
    }

    public String getSubject(){
        String sub = "Subject- "+subject;
        return sub;
    }

    public String getVenue(){
        String ven = "Venue- "+venue;
        return ven;
    }

    public Boolean getHoliday(){
        return holiday;
    }

    public String getHolidayMessage() {
        return holidayMessage;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
