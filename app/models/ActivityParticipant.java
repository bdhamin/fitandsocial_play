package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by mint on 1-9-14.
 */
@Entity
@Table(name = "participants")
public class ActivityParticipant{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Users user;
    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name="ACTIVITY_PARTICIPANTS", joinColumns={@JoinColumn(referencedColumnName="ID")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="ID")})
    private List<Activity> activities;
    private boolean isParticipationActive;
    private long participationDate;
    private long cancellationDate;

    public ActivityParticipant(){
        setParticipationActive(true);
        setParticipationDate(new Date().getTime());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public boolean isParticipationActive() {
        return isParticipationActive;
    }

    public void setParticipationActive(boolean isParticipationActive) {
        this.isParticipationActive = isParticipationActive;
    }

    public long getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(long participationDate) {
        this.participationDate = participationDate;
    }

    public long getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(long cancellationDate) {
        this.cancellationDate = cancellationDate;
    }
}
