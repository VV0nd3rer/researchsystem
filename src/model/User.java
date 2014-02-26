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
        Vector/*<Vector<String>>*/ DLPSystems = new Vector/*<Vector<String>>*/();
    
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
    
  public void editDLPSystems(Vector _num, Vector _data) {
      String userQuery = "UPDATE dlp_systems "
                          + "set title = ?, information = ?, country = ?, offical_site =? " 
                          +"where system_id = ?";
        PreparedStatement userStmt = null;
        
        try {
            userStmt = connection.prepareStatement(userQuery);
            for(int i = 0; i < _num.size(); i++) {
                for(int j = 0; j < _data.size(); j++) {
                    if( ((Vector)_data.get(j)).get(0) == _num.get(i)) {
                        for (int k = 1; k < 5; k++) {
                        //Oooppps, TODO - in loop
                        userStmt.setString(k, ((Vector)_data.get(j)).get(k).toString());
//                        userStmt.setString(2, ((Vector)_data.get(j)).get(2).toString());
//                        userStmt.setString(3, ((Vector)_data.get(j)).get(3).toString());
//                        userStmt.setString(4, ((Vector)_data.get(j)).get(4).toString());
                        }
                        userStmt.setInt(5, (Integer)_num.get(i));
                        userStmt.executeUpdate();
                    }
                }
            }
        }
        catch(SQLException ex) {
             System.out.println("Error in editDLPSystems method");
        }
  }
    
  public void createDLPSystem(Vector _data) {
       String userQuery = "INSERT INTO dlp_systems (title, information, country, offical_site) values "
                           + "(?,?,?,?)";
        PreparedStatement userStmt = null;
        
        try {
            userStmt = connection.prepareStatement(userQuery);
            
            for (int i = 0; i < _data.size(); i ++) {
                //System.out.println("data [" + i + "]" + ((Vector)_data.get(i)).get(0).toString());
                if (((Vector)_data.get(i)).get(0) == "") {
                    System.out.println("Is null");
                    for (int k = 1; k < 5; k++) {
                        userStmt.setString(k, ((Vector)_data.get(i)).get(k).toString());
                    }
                }
            }
             userStmt.executeUpdate();     
        }
        catch(SQLException ex) {
             System.out.println("Error in createDLPSystems method");
        }
  }
  
  public void deleteDLPSystem(int _num) {
       String userQuery = "Delete from dlp_systems where system_id = ?";
        PreparedStatement userStmt = null;
        
        try {
            userStmt = connection.prepareStatement(userQuery);
            userStmt.setInt(1, _num);      
            userStmt.executeUpdate();     
        }
        catch(SQLException ex) {
             System.out.println("Error in deleteDLPSystems method");
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
                for (int i = 1; i < rowCount + 1; i++) {
                   //System.out.println("rs object [" + i + "] " + rs.getObject(i));
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
    
    public Vector/*<Vector<String>>*/ getDLPSystems() {
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
