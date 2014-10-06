package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by mint on 1-9-14.
 */
@Entity
public class AuthenticationProvider {

    @Id
    private String providerKey;
    @OneToOne(mappedBy = "authenticationProvider")
    private Users user;
    //Should this be change to an enum list?
    private String providerType;

    public String getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }
}
