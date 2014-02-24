package model;

import java.sql.*;
import java.util.*;

public class User {
    private
        Connection connection = null;
        String login;
        String pass;
        int userId;
        String userName;
        Vector DLPSystems = new Vector();
    
    public User() {
        connection = new ConnectionDB().getConnection();
    }
    
    public void findUser() {
//        System.out.println("In findUser method.");
        String userQuery = "Select * from analysts where login = ? and password = ?";
        PreparedStatement userStmt = null;
        ResultSet userRs = null; 
        
        try {
            userStmt = connection.prepareStatement(userQuery);
            userStmt.setString(1, login);
            userStmt.setString(2, pass);
            
             userRs = userStmt.executeQuery();
             while (userRs.next()) {
                 userId = (Integer)userRs.getObject(1);
                 System.out.println("userId: " + userId);
                 userName = userRs.getObject(2).toString();
             }
        }
        catch(SQLException ex) {
             System.out.println("Error in findUser method");
        }
  }
    
    public void findDLPSystems() {
        String query = "SELECT * FROM dlp_systems";
        ResultSetMetaData rsmd = null;
        Statement sm = null;
        ResultSet rs = null;
        Vector row = new Vector();
        try {
            sm = connection.createStatement();
            rs = sm.executeQuery(query);
            rsmd = rs.getMetaData();
            int rowCount = rsmd.getColumnCount();
           
            while (rs.next()) {
                for (int i = 2; i < rowCount; i++) {
                   row.addElement(rs.getObject(i));
                }
                DLPSystems.add(row);
                row = new Vector(rowCount);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Could not execute query");    
        }
    }
    
    public Vector getDLPSystems() {
       return DLPSystems;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getPass() {
        return pass;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setLogin(String _login) {
        login = _login;
        System.out.println("login: " + login);
    }
    
    public void setPass(String _pass) {
        pass = _pass;
        System.out.println("pass: " + pass);
    }
    
}
