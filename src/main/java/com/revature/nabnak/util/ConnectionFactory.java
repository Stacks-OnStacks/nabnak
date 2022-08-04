package com.revature.nabnak.util;


/*
    Singleton Desgin Pattern
        - Creational pattern, that restricts only a single instance of the class can be made withint he application
    Factory Design Pattern
        - Creational, used to abstract away creation and instantiation of the class
        - churn out the  instances of Connection to other files
 */

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    // This class OPENs connections and delivers them to our DAO, try-with-resources is very useful

    private static final ConnectionFactory connectionFactory = new ConnectionFactory(); // eagerly instantiated singleton
    private Properties props = new Properties();

    private ConnectionFactory(){ // default no arg constructor is public, singletons don't want that
        try {
            props.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static { // static block, just runs everything inside the {} (block) at class loading
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    // getter for our instance of a ConnectionFactory
    public static ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
