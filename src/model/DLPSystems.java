package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.*;

public class DLPSystems extends ModelAction {
    
    Connection connection = null;
    Vector records = new Vector();
    
    public DLPSystems() {
        connection = ConnectionDB.getInstance().getConnection();
    }
    
    public void updateDLPSystems(Vector _num, Vector _data) {
      String userQuery = "UPDATE dlp_systems "
                          + "set title = ?, information = ?, country = ?, offical_site =? " 
                          +"where system_id = ?";
        updateRecords(_num, _data, userQuery);
  }
    
  public void createDLPSystems(Vector _data) {
       String userQuery = "INSERT INTO dlp_systems (title, information, country, offical_site) values "
                           + "(?,?,?,?)";
       createRecord(_data, userQuery);
  }
  
  public void deleteDLPSystem(int _num) {
        String userQuery = "Delete from dlp_systems where system_id = ?";
        deleteRecord(_num, userQuery);
}
  
    private void findDLPSystems() {
        String findSQL = "SELECT * FROM dlp_systems";
        records = findRecords(findSQL);
//        try {
//            ResultSet rs = findRecords(findSQL);
//            while (rs.next()) {
//                records.add(rs.getString("system_id"));
//                records.add(rs.getString("title"));
//                records.add(rs.getString("information"));
//                records.add(rs.getString("country"));
//            }
//        }
//        catch (SQLException ex) {
//            System.out.println("Error in findDLPSystems" + ex);
//        }
    }
    
    public Vector getRecords() {
       findDLPSystems();
       return records;
    }
}
