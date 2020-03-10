package com.pee.hibernateoneone.model;

import javax.persistence.*;

@Entity
@Table(name = "t1_driver_card")
public class DriverCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int personId;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "exp_date")
    private String expDate;

    @OneToOne(mappedBy = "driverCard", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private Person person;

    public DriverCard() {
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "DriverCard{" +
                "personId=" + personId +
                ", startDate='" + startDate + '\'' +
                ", expDate='" + expDate + '\'' +
                '}';
    }
}
