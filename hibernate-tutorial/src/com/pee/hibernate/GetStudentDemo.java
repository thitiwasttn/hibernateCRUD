package com.pee.hibernate;

import com.pee.jdbc.TestJdbc;
import com.pee.model.Student;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class GetStudentDemo {
    final static Logger logger = Logger.getLogger(GetStudentDemo.class);

    public static void main(String[] args) {
        TestJdbc.setLog();
//        logger.error("test error");
        //read config xml
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        logger.debug(factory);

        //read
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
//            Student student = session.get(Student.class,3);
//            logger.debug(student.toString());
//            getAllStudent(session);
//            customQuery(session);
            List<Student> students = session.createQuery("from Student where personCode = 'uumtgpvclwcmzxkki'").list();

            logger.debug("student id === >>"+students.get(0).getId());
        } catch (Exception ex) {
            logger.debug(ex.toString());
        } finally {
            try {
                factory.close();
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
        }
    }


    private static void customQuery(Session session) throws Exception {
        String hSQL = "from Student where id=1058";
        List<Student> students = session.createQuery(hSQL).list();
        logger.debug("hsql ;;; >>>> " + hSQL);
        logger.debug("================= customQuery ==============");
        logger.debug("length student == > " + students.size());
        for (int i = 0; i < students.size(); i++) {
            logger.debug("row " + i + ":");
            logger.debug(students.get(i).toString());
        }
    }

    private static void getAllStudent(Session session) throws Exception {
        List<Student> students = session.createQuery("from Student ").list();
        logger.debug("================= test ==============");
        logger.debug("length student == > " + students.size());
        for (int i = 0; i < students.size(); i++) {
            logger.debug("row " + i + ":");
            logger.debug(students.get(i).toString());
        }
    }
}
