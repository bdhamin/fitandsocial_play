package models;

import javax.persistence.*;

/**
 * Created by mint on 8-10-14.
 */

@Entity
public class UserGCM {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "userGCM")
    private Users user;
//    @OneToOne
//    private GCM userGCM;
    @Column(unique = true)
    private String deviceRegistrationId;


    public String getDeviceRegistrationId() {
        return deviceRegistrationId;
    }

    public void setDeviceRegistrationId(String deviceRegistrationId) {
        this.deviceRegistrationId = deviceRegistrationId;
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
}
