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
    private double startLocationLatitude;
    private double startLocationMagnitude;
    private double endLocationLatitude;
    private double endLocationMagnitude;
    @OneToOne(mappedBy = "activityLocation")
    private Activity activity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public double getStartLocationLatitude() {
        return startLocationLatitude;
    }

    public void setStartLocationLatitude(double startLocationLatitude) {
        this.startLocationLatitude = startLocationLatitude;
    }

    public double getStartLocationMagnitude() {
        return startLocationMagnitude;
    }

    public void setStartLocationMagnitude(double startLocationMagnitude) {
        this.startLocationMagnitude = startLocationMagnitude;
    }

    public double getEndLocationLatitude() {
        return endLocationLatitude;
    }

    public void setEndLocationLatitude(double endLocationLatitude) {
        this.endLocationLatitude = endLocationLatitude;
    }

    public double getEndLocationMagnitude() {
        return endLocationMagnitude;
    }

    public void setEndLocationMagnitude(double endLocationMagnitude) {
        this.endLocationMagnitude = endLocationMagnitude;
    }
}
