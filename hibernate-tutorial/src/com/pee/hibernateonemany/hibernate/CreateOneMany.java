package com.pee.hibernateonemany.hibernate;

import com.pee.hibernateonemany.model.DriverCard;
import com.pee.hibernateonemany.model.Person;
import com.pee.hibernateonemany.model.YoutubeChannel;
import com.pee.jdbc.TestJdbc;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateOneMany {
    Logger logger = Logger.getLogger(CreateOneMany.class);

    public static void main(String[] args) {
        TestJdbc.setLog();
        CreateOneMany createOneMany = new CreateOneMany();
//        createOneMany.addManyToOne();
//        createOneMany.addYoutube();
//        createOneMany.addPerson("thitiwas","toto","saad@gmail.com");
//        createOneMany.addDriverCard("10","40",);
        try {
            Person person = createOneMany.getPerson(22);
            createOneMany.logger.debug("person person >>> " + person);
//            int driverCardId = createOneMany.addDriverCardToPerson("3/10/2020", "5/10/2020", person.getPersonCode());
//            createOneMany.logger.debug("driverCardId" + driverCardId);
            int driverCardId = person.getDriverCard().getDriverCardId();
            createOneMany.updateDriverCard("11/12/2019", "6/12/2020", driverCardId);
        } catch (Exception ex) {
            createOneMany.logger.error(ex.toString());
        }
    }

    private void addManyToOne() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(YoutubeChannel.class)
                .addAnnotatedClass(DriverCard.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            int t1PersonId = 18;
            Person person = session.get(Person.class, t1PersonId);
            logger.debug("person +=== > " + person);
            logger.debug("youtube channel " + person.getYoutubeChannels());
            YoutubeChannel smile = new YoutubeChannel("smile", "200000");
            YoutubeChannel player = new YoutubeChannel("player", "670000");
            smile.setPerson(person);
            player.setPerson(person);

            logger.debug("======= save ========");
            session.save(smile);
            session.save(player);
            logger.debug("person youtube" + person.getYoutubeChannels());
            logger.debug("====== done ========");
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
            factory.close();
        }
    }

    private void getYoutube() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(YoutubeChannel.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(DriverCard.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            int youtubeId = 1;
            YoutubeChannel youtubeChannel = session.get(YoutubeChannel.class, youtubeId);
            logger.debug("youtube +=== > " + youtubeChannel);
//            logger.debug("youtube channel "+ person.getYoutubeChannels());
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
            factory.close();
        }
    }

    private void addYoutube() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(YoutubeChannel.class)
                .addAnnotatedClass(DriverCard.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            logger.debug("======= get person =====");
            Person person = session.get(Person.class, 1);
            logger.debug("person person == > " + person);
            YoutubeChannel youtubeChannel = new YoutubeChannel("peeTest", "1124");
            youtubeChannel.setPerson(person);
            logger.debug("===== save =====");
            session.save(youtubeChannel);


            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
            factory.close();
        }
    }

    private int addPerson(String fName, String lName, String email) {

        int result = 0;
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(YoutubeChannel.class)
                .addAnnotatedClass(DriverCard.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            logger.debug("==== add one person ===");
            Person person = new Person();
            person.setEmai(email);
            person.setFirstName(fName);
            person.setLastName(lName);

            result = (int) session.save(person);
            logger.debug("result = " + result);

            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
            factory.close();
        }

        return result;
    }

    private int addDriverCardToPerson(String startDate, String expDate, int personId) {
        int result = 0;
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(YoutubeChannel.class)
                .addAnnotatedClass(DriverCard.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            Person person = session.get(Person.class, personId);
            logger.debug("person in update driver card == > " + person);

//            session = factory.getCurrentSession();
//            session.beginTransaction();


            DriverCard driverCard = new DriverCard();
            driverCard.setStartDate(startDate);
            driverCard.setExpDate(expDate);
            person.setDriverCard(driverCard);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            result = session.get(Person.class, personId).getDriverCard().getDriverCardId();
        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
            factory.close();
        }

        return result;
    }

    private Person getPerson(int personId) {
        Person person = new Person();
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(YoutubeChannel.class)
                .addAnnotatedClass(DriverCard.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            logger.debug("==== get one person ===");
            person = session.get(Person.class, personId);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
            factory.close();
        }

        return person;
    }

    private int updateDriverCard(String startDate, String expDate, int driverCardId) {
        int result = 0;

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(YoutubeChannel.class)
                .addAnnotatedClass(DriverCard.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            DriverCard driverCard = session.get(DriverCard.class, driverCardId);
            logger.debug("driver card id " + driverCard);

            driverCard.setExpDate(expDate);
            driverCard.setStartDate(startDate);
            logger.debug("===== update ======");
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
            factory.close();
        }

        return result;
    }
}
