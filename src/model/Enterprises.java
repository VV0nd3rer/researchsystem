package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Enterprises extends ModelAction {
    Connection connection = null;
    Vector records = new Vector(); 
    
    public Enterprises() {
        connection = ConnectionDB.getInstance().getConnection();
    }
    
    private void findEnterprises() {
        String findSQL = "SELECT * FROM audits";
        records = findRecords(findSQL);
    }
    
    private void findAudits() {
        String findSQL = "SELECT *  FROM audits, enterprises, dlp_systems, security_level " +
                             "where audits.enterprise_id = enterprises.enterprise_id and " +
                             "audits.start_dlp_id = dlp_systems.system_id and " +
                             "audits.start_level_id = security_level.level_id";
        records = findRecords(findSQL);
        for (int i = 0; i < records.size(); i ++) {
            //
        }
//        ResultSet rs = findRec(findSQL);
//        try {
//             while (rs.next()) {
//               records.add(rs.getString("name"));
//               records.add(rs.getString("title"));
//               records.add(rs.getString("level"));
//             }
//        }
//        catch (SQLException ex) {
//            
//        }
       
    }
    
    public void updateEnterprise(Vector _num, Vector _data) {
        //Update composite table
//        String userQuery = "UPDATE enterprises "
//                          + "name = ?, information = ?, adress = ? " 
//                          +"where system_id = ?";
//        updateRecords(_num, _data, userQuery);
    }
    
    public void createEnterprise(Vector _data) {
//       String userQuery = "INSERT INTO dlp_systems (title, information, country, offical_site) values "
//                           + "(?,?,?,?)";
//       createRecord(_data, userQuery);
    }
    
    public void deleteEnterprise(int _num) {
//         String userQuery = "Delete from dlp_systems where system_id = ?";
//         deleteRecord(_num, userQuery);
    }
    
    public Vector getRecords() {
        findAudits();
        return records;
    }
    
}
