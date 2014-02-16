package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private 
            Connection connect = null;
    
    public ConnectionDB() {
            Connect();
    }
    
    private void Connect() {
        String bdUser = "root";
        String dbPassword = "1111";
        String dbUrl = "jdbc:mysql://localhost/research";
        
        try {
            connect = DriverManager.getConnection(dbUrl, bdUser, dbPassword);
            System.out.println("Connected to database successfully!");
         }  
         catch(SQLException ex)
         {
            System.out.println("Could not connect to database");    
         }
}
    
    public Connection getConnection() {
        return connect;
    }
}
