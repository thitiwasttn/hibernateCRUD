package com.pee.hibernateoneone.hibernate;

import com.pee.hibernateoneone.model.DriverCard;
import com.pee.hibernateoneone.model.Person;
import com.pee.jdbc.TestJdbc;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteOneOne {
    private static Logger logger = Logger.getLogger(DeleteOneOne.class);

    public static void main(String[] args) {
        TestJdbc.setLog();
        DeleteOneOne deleteOneOne = new DeleteOneOne();
        deleteOneOne.deleteOnlyDriverCard();
    }

    private void deleteOneOne()
    {
        SessionFactory personFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(DriverCard.class)
                .buildSessionFactory();
        logger.debug(personFactory);
        Session session = personFactory.getCurrentSession();
        session.beginTransaction();

        try {
            logger.debug("========== delete =========");
            int personId = 7;
            Person person = session.get(Person.class,personId);
            logger.debug("person === > " + person.toString());

            if(person != null)
            {
                logger.debug("delete....." + person.toString());
                session.delete(person);
            }

            session.getTransaction().commit();
        }catch (Exception ex)
        {
            logger.error(ex.toString());
        }finally {
            session.close();
        }
    }

    private void deleteDriverCard()
    {
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
            logger.debug("delete card == > " + driverCard.toString());

            logger.debug("person in driver card == > " + driverCard.getPerson());

//            logger.debug("delete");
            session.delete(driverCard);

            session.getTransaction().commit();

        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
        }
    }

    private void deleteOnlyDriverCard()
    {
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
            int id = 14;
            DriverCard driverCard = session.get(DriverCard.class, id);
            driverCard.getPerson().setDriverCard(null);
            logger.debug("driver card == > " + driverCard.toString());

            logger.debug("person in driver card == > " + driverCard.getPerson());


//            logger.debug("delete");
            session.delete(driverCard);

            session.getTransaction().commit();

        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            session.close();
        }
    }
}
