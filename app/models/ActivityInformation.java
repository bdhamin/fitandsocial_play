package models;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

import utils.*;
import utils.DateFormat;

/**
 * Created by mint on 1-9-14.
 */
@Entity
public class ActivityInformation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String type;
    private int duration;
    private int distance;
    @Transient
    private String activityDateString;
//    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
//    @DateFormat("dd-MM-yyyy")
//    private DateTime activityDate;
    @DateFormat("dd-MM-yyyy")
    long activityDate;
    @Transient
    private String activityTimeString;
    @TimeFormat("HH:mm")
    private long activityTime;
    @OneToOne(mappedBy = "activityInformation")
    private Activity activity;

    public ActivityInformation(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public long getActivityDate() {

        return activityDate;
    }

    public void setActivityDateString(String activityDateString){
        this.activityDateString = activityDateString;
    }


    public String getActivityDateString(){

        return DateAndTimePrintFormatter.dateTimePrintFormatter(this.getActivityDate());
    }

    public void setActivityDate(long activityDate) {

        this.activityDate = activityDate;
    }

    public long getActivityTime() {

        return activityTime;
    }

    public void setActivityTime(long activityTime) {
        this.activityTime = activityTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getActivityTimeString() {
        activityTimeString = DateAndTimePrintFormatter.timePrintFormatter(this.getActivityTime());
        return activityTimeString;
    }

}
