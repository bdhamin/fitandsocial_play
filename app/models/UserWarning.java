package models;

import javax.persistence.*;

/**
 * Created by mint on 24-9-14.
 */

@Entity
public class UserWarning {

    @Id
    @GeneratedValue
    private Long id;
    private long warningDate;
    private String warningText;
    @ManyToOne
    private Users user;

    public UserWarning(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getWarningDate() {
        return warningDate;
    }

    public void setWarningDate(long warningDate) {
        this.warningDate = warningDate;
    }

    public String getWarningText() {
        return warningText;
    }

    public void setWarningText(String warningText) {
        this.warningText = warningText;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
