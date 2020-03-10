package com.pee.hibernateoneone.hibernate;

import com.pee.hibernateoneone.model.DriverCard;
import com.pee.hibernateoneone.model.Person;
import com.pee.jdbc.TestJdbc;
import com.pee.model.Student;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateOneOne {
    private static Logger logger = Logger.getLogger(CreateOneOne.class);

    public static void main(String[] args) {
        TestJdbc.setLog();
        CreateOneOne createOneOne = new CreateOneOne();
//        for (int i = 0; i < 3; i++) {
//            createOneOne.addOneDateInT1_Person();
//        }
//        createOneOne.addOneDateInT1_Person();
    }

    private void addOneDateInT1_Person() {
        SessionFactory personFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(DriverCard.class)
                .buildSessionFactory();
        logger.debug(personFactory);

        Session session = personFactory.getCurrentSession();
        session.beginTransaction();
        try {
            logger.debug("====== create t1_person =======");
            Person person = new Person();
            DriverCard driverCard = new DriverCard();
            person.setFirstName("thitiwas");
            person.setLastName("nupan");
            person.setEmai("twopee26@gmail.com");

            driverCard.setStartDate("3/3/2020");
            driverCard.setExpDate("3/3/2030");
//
            person.setDriverCard(driverCard);

            session.save(person);
//            session.save(driverCard);
            session.getTransaction().commit();
            logger.debug("======= save ==========");

        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
        }
    }

    private void getOneDateInT1_driverCard() {

        SessionFactory personFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(DriverCard.class)
                .buildSessionFactory();
        logger.debug(personFactory);

        Session session = personFactory.getCurrentSession();
        session.beginTransaction();
        try {
            logger.debug("====== get t1_driver_card =======");
            int id = 7;
            DriverCard driverCard = session.get(DriverCard.class, id);
            logger.debug("driver card == > " + driverCard.toString());

            logger.debug("person in driver card == > " + driverCard.getPerson());


            session.getTransaction().commit();

        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
        }

    }
}
