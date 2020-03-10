package com.pee.hibernateonemany.model;

//import com.pee.hibernateoneone.model.DriverCard;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t1_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int personCode;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String emai;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_card_id")
    private DriverCard driverCard;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.PERSIST})
    private List<YoutubeChannel> youtubeChannels;



    public void addYoutubeChannel(YoutubeChannel youtubeChannel)
    {
        if(youtubeChannels != null)
        {
            youtubeChannels = new ArrayList<>();
        }
        youtubeChannels.add(youtubeChannel);
        youtubeChannel.setPerson(this);
    }




    public List<YoutubeChannel> getYoutubeChannels() {
        return youtubeChannels;
    }

    public void setYoutubeChannels(List<YoutubeChannel> youtubeChannels) {
        this.youtubeChannels = youtubeChannels;
    }

    public DriverCard getDriverCard() {
        return driverCard;
    }

    public void setDriverCard(DriverCard driverCard) {
        this.driverCard = driverCard;
    }

    public int getPersonCode() {
        return personCode;
    }

    public void setPersonCode(int personCode) {
        this.personCode = personCode;
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

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personCode=" + personCode +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emai='" + emai + '\'' +
                ", driverCard=" + driverCard +
                '}';
    }
}
