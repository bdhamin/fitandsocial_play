package models;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import javax.persistence.*;
import java.util.List;

/**
 * Created by mint on 24-8-14.
 */

@Entity
public class Activity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationDate;
    @OneToOne(cascade = CascadeType.ALL)
    private ActivityInformation activityInformation;
    @OneToOne(cascade = CascadeType.ALL)
    private ActivityLocation activityLocation;
    /**
     * Used the fetch type used here is not the recommended when loading depth dependencies.
     * However here we always need to access depth dependencies (User, User information) therefore
     * this option is ok. A better solution would be lazy loading with an option to for eager loading
     * when needed, but again this is not an option "right now".
     */
    @ManyToMany(mappedBy="activities", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<ActivityParticipant> activityParticipants;
    private String activityStatus;
    @ManyToOne
    private Users user;
    private boolean isOwner;

    public Activity(){
        this.creationDate = new DateTime();
        //TODO: change the status to enum
        this.activityStatus = "Active";
        this.isOwner = true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ActivityInformation getActivityInformation() {
        return activityInformation;
    }

    public void setActivityInformation(ActivityInformation activityInformation) {
        this.activityInformation = activityInformation;
    }

    public ActivityLocation getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(ActivityLocation activityLocation) {
        this.activityLocation = activityLocation;
    }

    public List<ActivityParticipant> getActivityParticipants() {
        return activityParticipants;
    }

    public void setActivityParticipants(List<ActivityParticipant> activityParticipants) {
        this.activityParticipants = activityParticipants;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }
}
