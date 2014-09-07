package models;

import javax.persistence.*;

/**
 * Created by mint on 1-9-14.
 */

@Entity
public class ActivityLocation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long startLocationLatitude;
    private long startLocationMagnitude;
    private long endLocationLatitude;
    private long endLocationMagnitude;
    @OneToOne(mappedBy = "activityLocation")
    private Activity activity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getStartLocationLatitude() {
        return startLocationLatitude;
    }

    public void setStartLocationLatitude(long startLocationLatitude) {
        this.startLocationLatitude = startLocationLatitude;
    }

    public long getStartLocationMagnitude() {
        return startLocationMagnitude;
    }

    public void setStartLocationMagnitude(long startLocationMagnitude) {
        this.startLocationMagnitude = startLocationMagnitude;
    }

    public long getEndLocationLatitude() {
        return endLocationLatitude;
    }

    public void setEndLocationLatitude(long endLocationLatitude) {
        this.endLocationLatitude = endLocationLatitude;
    }

    public long getEndLocationMagnitude() {
        return endLocationMagnitude;
    }

    public void setEndLocationMagnitude(long endLocationMagnitude) {
        this.endLocationMagnitude = endLocationMagnitude;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
