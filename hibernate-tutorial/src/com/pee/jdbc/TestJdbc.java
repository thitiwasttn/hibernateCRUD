package com.pee.jdbc;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class TestJdbc {
    final static Logger logger = Logger.getLogger(TestJdbc.class);

    public TestJdbc() {
        setLog();
    }

    public static void main(String[] args) {
        TestJdbc testJdbc = new TestJdbc();


//        System.out.println(generatedString);
//        String jdbc = "jdbc:mysql://localhost:3306/udemy_demo?useSSL=false&serverTimezone=UTC";
////        String jdbc = "jdbc:postgresql://61.90.160.212:5432/minerva";
//        String user = "root";
//        String pass = "dewsfmsx123";
////        String user = "minerva";
////        String pass = "Mu#@98731!";
//
////        logger.debug(jdbc);
//        try {
//            logger.debug("Connecting to db ==> "+ jdbc);
//            Connection connection = DriverManager.getConnection(jdbc,user,pass);
//            logger.debug(connection);
//
//            logger.debug("=========== complete ==========");
////            testJdbc.testGetStudent(connection);
//        }catch (Exception ex)
//        {
//            logger.debug("error :: >>> "+ex.toString());
//        }
    }
    public static void setLog()
    {
        PropertyConfigurator.configure("C:\\logJar\\log4j.properties");
        logger.debug("==================== start ====================");
    }
    private void testGetStudent(Connection connection) throws Exception
    {
        String sql = "select * from m_student";
        ResultSet resultset;
        PreparedStatement preparedStatement;
        preparedStatement =connection.prepareStatement(sql);
        resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            logger.debug(resultset.getString("first_name"));
        }
    }
}
