package com.pee.hibernate;

import com.pee.jdbc.TestJdbc;
import com.pee.model.Student;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    private static Logger logger = Logger.getLogger(DeleteStudentDemo.class);

    public static void main(String[] args) {
        TestJdbc.setLog();
        SessionFactory studentFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = studentFactory.getCurrentSession();
        session.beginTransaction();
        try {
//            Student student = session.get(Student.class,1064);
//            logger.debug(student.toString());
            logger.debug("===== delete =====");
            session.createQuery("delete from Student where personCode = 'idzduzyueyxebqfmh'").executeUpdate();
            session.getTransaction().commit();

            logger.debug("=======delete======");
        } catch (Exception ex) {
            logger.error(ex);
        } finally {
            try {

            } catch (Exception ex) {
                logger.error(ex);
            }
        }
    }
}
