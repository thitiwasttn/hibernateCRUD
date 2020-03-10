package com.pee.hibernateonemany.model;

//import com.pee.hibernateoneone.model.Person;

import javax.persistence.*;

@Entity
@Table(name = "t1_driver_card")
public class DriverCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_card_id")
    private int driverCardId;
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

    public int getDriverCardId() {
        return driverCardId;
    }

    public void setDriverCardId(int personId) {
        this.driverCardId = personId;
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
                "driverCardId=" + driverCardId +
                ", startDate='" + startDate + '\'' +
                ", expDate='" + expDate + '\'' +
                '}';
    }
}
