package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Conn {

    //Driver
    static String jdbcDriver;
    //Url of Database
    static String dbUrl;

    //Desired Database to access
    static String dbName;

    //Credentials
    static String dbUser;
    static String dbPassword;

    static Connection conn = null;

    public Conn() {
        getConnectionInfo();
    }

    public static void closeConnection() throws SQLException {
        //Close connection
        if (conn != null) conn.close();
    }

    public static Connection getConnection() {
        if (conn != null)
            return conn;
        try {
            //Register JDBC driver
            Class.forName(jdbcDriver);
            //Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(dbUrl + dbName, dbUser, dbPassword);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error Class Not Found", e);
        }
    }

    private void getConnectionInfo() {
        try (InputStream input = Conn.class.getResourceAsStream("/WEB-INF/config.properties")){
	    
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value
            jdbcDriver = prop.getProperty("db.driver");
            dbUrl = prop.getProperty("db.url");
            dbUser = prop.getProperty("db.user");
            dbPassword = prop.getProperty("db.password");
            dbName = prop.getProperty("db.name");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
