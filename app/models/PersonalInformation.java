package models;

import javax.persistence.*;

/**
 * Created by mint on 1-9-14.
 */
@Entity
public class PersonalInformation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public int age;
    public String gender;
    public String firstName;
    public String lastName;
    public String userEmail;
    @OneToOne(mappedBy = "personalInformation")
    public Users user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
