package models;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by mint on 1-9-14.
 */
@Entity
public class UserHistory{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime activeSince;
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastActive;
    private int totalCreatedActivities;
    private int totalParticipation;
    private int totalCancelledActivities;
    private int totalWarnings;
    @OneToOne(mappedBy = "userHistory")
    private Users user;

    public UserHistory(){
        this.setActiveSince(new DateTime());
        this.setLastActive(new DateTime());
        this.setTotalCreatedActivities(0);
        this.setTotalParticipation(0);
        this.setTotalCancelledActivities(0);
        this.setTotalWarnings(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(DateTime activeSince) {
        this.activeSince = activeSince;
    }

    public DateTime getLastActive() {
        return lastActive;
    }

    public void setLastActive(DateTime lastActive) {
        this.lastActive = lastActive;
    }

    public int getTotalCreatedActivities() {
        return totalCreatedActivities;
    }

    public void setTotalCreatedActivities(int totalCreatedActivities) {
        this.totalCreatedActivities = totalCreatedActivities;
    }

    public int getTotalParticipation() {
        return totalParticipation;
    }

    public void setTotalParticipation(int totalParticipation) {
        this.totalParticipation = totalParticipation;
    }

    public int getTotalCancelledActivities() {
        return totalCancelledActivities;
    }

    public void setTotalCancelledActivities(int totalCancelledActivities) {
        this.totalCancelledActivities = totalCancelledActivities;
    }

    public int getTotalWarnings() {
        return totalWarnings;
    }

    public void setTotalWarnings(int totalWarnings) {
        this.totalWarnings = totalWarnings;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
