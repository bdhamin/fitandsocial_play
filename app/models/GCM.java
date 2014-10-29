package models;

import javax.persistence.*;

/**
 * Created by mint on 8-10-14.
 */

@Entity
public class GCM {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String deviceRegistrationId;
//    @OneToOne(cascade = CascadeType.REMOVE)
//    private UserGCM userGCM;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceRegistrationId() {
        return deviceRegistrationId;
    }

    public void setDeviceRegistrationId(String deviceRegistrationId) {
        this.deviceRegistrationId = deviceRegistrationId;
    }

//    public UserGCM getUserGCM() {
//        return userGCM;
//    }
//
//    public void setUserGCM(UserGCM userGCM) {
//        this.userGCM = userGCM;
//    }
}
