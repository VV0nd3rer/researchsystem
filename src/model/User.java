package model;

import java.sql.*;
import java.util.*;

public class User extends ModelAction {
    private
        Connection connection = null;
        String login;
        String pass;
        int userId;
        String userName;
    
    public User() {
        connection = ConnectionDB.getInstance().getConnection();
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
    private Vector findExperts() {
        sqlQuery = "SELECT * FROM experts";
        records = findRecords(sqlQuery);
        return records;
    }
    public Vector getExperts() {
        return findExperts();
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
