package com.pee.hibernate;

import com.pee.jdbc.TestJdbc;
import com.pee.model.Student;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class UpdateStudentDemo {
    private static Logger logger = Logger.getLogger(UpdateStudentDemo.class);

    public UpdateStudentDemo() {

        TestJdbc.setLog();
        logger.debug("============= UpdateStudentDemo ===============");
    }

    public static void main(String[] args) {
        UpdateStudentDemo usd = new UpdateStudentDemo();

        SessionFactory studentFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = studentFactory.getCurrentSession();
        try {
            String personCode = "oaoohihigvxopuors";
            int studentId = 1144;
            session.beginTransaction();
            logger.debug("studentId for update === > " + studentId);
            Student student = session.get(Student.class,studentId);
            logger.debug("student === > "+ student.toString());

            //update 1
            student.setFirstName("after update");
            logger.debug("==== update === student === "+ student.toString());
//            session.getTransaction().commit();

            //update 2
            session.createQuery("update Student set email = 'afterUpdate@hibernate.com' where id="+studentId).executeUpdate();
            session.getTransaction().commit();
            logger.debug("==== done ====");

        }catch (Exception ex)
        {
            logger.error(ex.toString());
        }finally {
            try {
                studentFactory.close();
            }catch (Exception ex)
            {
                logger.error(ex.toString());
            }
        }
    }
}
