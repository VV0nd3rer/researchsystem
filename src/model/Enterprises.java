package model;

import java.sql.Connection;
import java.util.Vector;

public class Enterprises extends ModelAction {
    Connection connection = null;
    Vector records = new Vector(); 
    
    public Enterprises() {
        connection = ConnectionDB.getInstance().getConnection();
    }
    
    private void findEnterprises() {
        String findSQL = "SELECT enterprise_id, name, system_id, title FROM enterprises, dlp_systems where "
                + "enterprises.start_dlp_id = dlp_systems.system_id";
        records = findRecords(findSQL);
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
        findEnterprises();
        return records;
    }
    
}
