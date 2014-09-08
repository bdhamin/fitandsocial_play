package models;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import javax.persistence.*;
import java.util.List;

/**
 * Created by mint on 1-9-14.
 */
@Entity
public class GeneralNotification{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_GENERAL_NOTIFICATION", joinColumns = {@JoinColumn (referencedColumnName = "ID")}
    , inverseJoinColumns = {@JoinColumn(referencedColumnName = "ID")})
    private List<Users> users;
    private String title;
    private String message;
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime sendDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(DateTime sendDate) {
        this.sendDate = sendDate;
    }
}
