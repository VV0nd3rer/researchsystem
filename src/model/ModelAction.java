package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ModelAction {
    Connection connection = null;
    
    Vector records = new Vector(); 
    String sqlQuery = null;
    
    public ModelAction() {
        connection = ConnectionDB.getInstance().getConnection();
    }
    
    public void updateRecords(Vector _num, Vector _data, String _sql) {
         PreparedStatement userStmt = null;
        
        try {
            userStmt = connection.prepareStatement(_sql);
            for(int i = 0; i < _num.size(); i++) {
                for(int j = 0; j < _data.size(); j++) {
                    if( ((Vector)_data.get(j)).get(0) == _num.get(i)) {
                        for (int k = 1; k < 5; k++) {
                            userStmt.setString(k, ((Vector)_data.get(j)).get(k).toString());
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
    public void createForeignRecord(Vector _data, String _sql) {
        PreparedStatement userStmt = null;
        
        try {
            userStmt = connection.prepareStatement(_sql);
            for (int i = 0; i < _data.size(); i ++) {
                 userStmt.setInt( i+1, (Integer)_data.get(i));
            }
             userStmt.executeUpdate();     
        }
        catch(SQLException ex) {
             System.out.println("Error in createRecord method");
        }
    }
    public void createRecord(Vector _data, String _sql) {
        PreparedStatement userStmt = null;
        
        try {
            userStmt = connection.prepareStatement(_sql);
            
            for (int i = 0; i < _data.size(); i ++) {
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
             System.out.println("Error in createRecord method");
        }
    }
    
    public void deleteRecord(int _num, String _sql) {
        PreparedStatement userStmt = null;
        
        try {
            userStmt = connection.prepareStatement(_sql);
            userStmt.setInt(1, _num);      
            userStmt.executeUpdate();     
        }
        catch(SQLException ex) {
             System.out.println("Error in deleteRecord method");
        }
    }
//    public Vector findRecords(String _sql) {
//        Vector records = new Vector();
//        ResultSetMetaData rsmd = null;
//        Statement sm = null;
//        ResultSet rs = null;
//        Vector row = new Vector();
//        try {
//            sm = connection.createStatement();
//            rs = sm.executeQuery(_sql);
//            rsmd = rs.getMetaData();
//            int rowCount = rsmd.getColumnCount();
//            
//            while (rs.next()) {
//                for (int i = 1; i < rowCount + 1; i++) {
//                   //System.out.println("rs object [" + i + "] " + rs.getObject(i));
//                   row.addElement(rs.getObject(i));
//                }
//                records.add(row);
//                row = new Vector(rowCount);
//            }
//        }
//        catch (SQLException ex)
//        {
//            System.out.println("Could not execute query");    
//        }
//        return records;
//    }
    public ResultSet findRecords(String sql) {
        //ResultSetMetaData rsmd = null;
        Statement sm = null;
        ResultSet rs = null;
        //Vector row = new Vector();
        try {
            sm = connection.createStatement();
            rs = sm.executeQuery(sql);
          //  rsmd = rs.getMetaData();
            //int rowCount = rsmd.getColumnCount();
            
//            while (rs.next()) {
//                for (int i = 1; i < rowCount + 1; i++) {
//                   //System.out.println("rs object [" + i + "] " + rs.getObject(i));
//                   row.addElement(rs.getObject(i));
//                }
//                records.add(row);
//                row = new Vector(rowCount);
//            }
        }
        catch (SQLException ex)
        {
            System.out.println("Could not execute query");    
        }
        return rs;
    }
    public Vector getRecords(ResultSet rs) {
        Vector records = new Vector();
        Vector row = new Vector();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int rowCount = rsmd.getColumnCount();
              while (rs.next()) {
                for (int i = 1; i < rowCount + 1; i++) {
                   row.addElement(rs.getObject(i));
                }
                records.add(row);
                row = new Vector(rowCount);
            }
        }
        catch (SQLException sql) {
            System.out.println("Error in fillRecords method. ModelAction.");
        }
        return records;
    }
//    public ResultSet findRec(String _sql) {
////        ResultSetMetaData rsmd = null;
//        Statement sm = null;
//        ResultSet rs = null;
//        Vector row = new Vector();
//        try {
//            sm = connection.createStatement();
//            rs = sm.executeQuery(_sql);
////            rsmd = rs.getMetaData();
////            int rowCount = rsmd.getColumnCount();
//            
////            while (rs.next()) {
////                for (int i = 1; i < rowCount + 1; i++) {
////                   //System.out.println("rs object [" + i + "] " + rs.getObject(i));
////                   row.addElement(rs.getObject(i));
////                }
////                records.add(row);
////                row = new Vector(rowCount);
////            }
//        }
//        catch (SQLException ex)
//        {
//            System.out.println("Could not execute query");    
//        }
//        return rs;
//    }
    
     public ResultSet findRecordsById(String _sql, int _num) {
        PreparedStatement userStmt = null;
//        Vector records = new Vector();
//        ResultSetMetaData rsmd = null;
        //Statement sm = null;
        ResultSet rs = null;
        //Vector row = new Vector();
        try {
            userStmt = connection.prepareStatement(_sql);
            userStmt.setInt(1, _num);      
            //sm = connection.createStatement();
            rs = userStmt.executeQuery();
//            rsmd = rs.getMetaData();
//            int rowCount = rsmd.getColumnCount();
            
//            while (rs.next()) {
//                System.out.println(rs.getString("title"));
//                records.addElement(rs.getObject(1));
//            }
        }
        catch (SQLException ex)
        {
            System.out.println("Could not execute query, " + ex.toString());    
        }
        return rs;
    }
    
    
}
