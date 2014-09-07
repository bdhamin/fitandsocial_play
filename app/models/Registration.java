package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by mint on 4-9-14.
 */
@Entity
public class Registration {

    @Id
    @GeneratedValue
    public Long id;

    public String name;



}
