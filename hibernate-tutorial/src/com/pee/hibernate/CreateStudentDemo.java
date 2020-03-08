package com.pee.hibernate;

import com.pee.jdbc.TestJdbc;
import com.pee.model.Student;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Random;

//import javax.security.auth.login.Configuration;

public class CreateStudentDemo {
    private static Logger logger = Logger.getLogger(CreateStudentDemo.class);

    public static void main(String[] args) {
        TestJdbc.setLog();

        //read config xml
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        logger.debug(factory);

        //create session
        Session session = factory.getCurrentSession();

        try {
            logger.debug("===== create student object =====");
            Student tempStudent;
            session.beginTransaction();
//            for (int i = 0; i < 250; i++) {
//                logger.debug("====in loop====  " + i);
//                tempStudent = new Student();
//                tempStudent.setEmail("test"+i+"@test.com");
//                tempStudent.setFirstName("thitiwas_"+i);
//                tempStudent.setLastName("lastname_"+i);
//                tempStudent.setPersonCode(getRandomPersonCode());
//
//                session.save(tempStudent);
//

//            }

            tempStudent = new Student("thitiwas", "nupan", "ex@ex.com",getRandomPersonCode());
            session.save(tempStudent);
            session.getTransaction().commit();
            logger.debug("=========== save done =========== ");
        } catch (Exception ex) {
            logger.debug("error ::: >" + ex.toString());
        } finally {
            try {
                factory.close();
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
        }
    }

    public static String getRandomPersonCode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 17;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
