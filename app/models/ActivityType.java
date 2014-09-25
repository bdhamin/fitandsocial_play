package models;

import javax.persistence.*;

/**
 * Created by mint on 1-9-14.
 */
//@Entity
public class ActivityType{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne
//    private GeneralUserInformation generalUserInformation;
    private String activityType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//
//    public GeneralUserInformation getGeneralUserInformation() {
//        return generalUserInformation;
//    }
//
//    public void setGeneralUserInformation(GeneralUserInformation generalUserInformation) {
//        this.generalUserInformation = generalUserInformation;
//    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
