package db;

import java.sql.*;

public class Conn{

    //Driver
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //Url of Database
    static final String DB_URL = "jdbc:mysql://localhost:3306/";

    //Desired Database to access
    static final String DB_NAME =  "PollMaster";
    
    //Credentials
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "";

    static Connection conn = null;    
    

    public static void closeConnection() throws SQLException{
        //Close connection
        if(conn!=null) conn.close();
    }

    public static Connection getConnection() {
	if (conn != null)
	    return conn;
	try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PASSWORD);
            return conn;
        } catch (SQLException e){
            throw new RuntimeException("Error connecting to database",e);
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Error Class Not Found",e);
        }
    }
    
    
}
