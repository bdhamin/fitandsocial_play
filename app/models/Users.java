package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mint on 24-8-14.
 */

@Entity
public class Users{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private AuthenticationProvider authenticationProvider;
    @OneToOne(cascade = CascadeType.ALL)
    private GeneralUserInformation userInformation;
    @OneToOne(cascade = CascadeType.ALL)
    public UserHistory userHistory;
    @OneToOne(cascade = CascadeType.ALL)
    private PersonalInformation personalInformation;
    @OneToMany
    private List<UserNotification> userNotification;
    @ManyToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    private List<GeneralNotification> generalNotifications;
    @OneToMany
    private List<Activity> activities;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ActivityParticipant> activityParticipants;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserWarning> userWarnings;
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @OneToOne(cascade = CascadeType.ALL)
    private UserGCM userGCM;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public GeneralUserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(GeneralUserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public UserHistory getUserHistory() {
        return userHistory;
    }

    public void setUserHistory(UserHistory userHistory) {
        this.userHistory = userHistory;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public List<UserNotification> getUserNotification() {
        return userNotification;
    }

    public void setUserNotification(List<UserNotification> userNotification) {
        this.userNotification = userNotification;
    }

    public List<GeneralNotification> getGeneralNotifications() {
        return generalNotifications;
    }

    public void setGeneralNotifications(List<GeneralNotification> generalNotifications) {
        this.generalNotifications = generalNotifications;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<ActivityParticipant> getActivityParticipants() {
        return activityParticipants;
    }

    public void setActivityParticipants(List<ActivityParticipant> activityParticipants) {
        this.activityParticipants = activityParticipants;
    }

    public List<UserWarning> getUserWarnings() {
        return userWarnings;
    }

    public void setUserWarnings(List<UserWarning> userWarnings) {
        this.userWarnings = userWarnings;
    }

    public UserGCM getUserGCM() {
        return userGCM;
    }

    public void setUserGCM(UserGCM userGCM) {
        this.userGCM = userGCM;
    }
}
