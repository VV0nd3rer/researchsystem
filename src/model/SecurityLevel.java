package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SecurityLevel extends ModelAction {
    Connection connection = null;
    Vector records = new Vector(); 
    private String selectEnterpriseName = null;
    private int selectEnterpriseId;
    
    public SecurityLevel() {
        connection = ConnectionDB.getInstance().getConnection();
    }
    public void setSelectEnterpriseName(String name) {
        selectEnterpriseName = name;
    }
    public String getSelectEnterpriseName() {
        return selectEnterpriseName;
    }
    public void setSelectEnterpriseId(int id) {
        selectEnterpriseId = id;
    }
    public int getSelectEnterpriseId() {
        return selectEnterpriseId;
    }
    private void findSecurityLevel() {
        String findSQL = "SELECT * from security_level where level_id < 4";
        records = getRecords(findRecords(findSQL));
//        try {
//            ResultSet rs = findRecords(findSQL);
//            while (rs.next()) {
//                records.addElement(rs.getString("level"));
//                records.addElement(rs.getString("description"));
//            }
//        }
//        catch (SQLException ex) {
//            System.out.println("Error in findSecurityLevel" + ex);
//        }
    }
    
    private void findThreats() {
        String findSQL = "SELECT * FROM threats";
        records = getRecords(findRecords(findSQL));
    }
    
//    private void findSelectedDLP(int _id) {
//        String findSQL = "SELECT title from dlp_systems where system_id = ?";
//        ResultSet rs = findParamRecords(findSQL, _id);
//        fillRecords(rs);
//    }
//    private void fillRecords(ResultSet rs) {
//        try {
//            while (rs.next()) {
//                records.add(rs.getObject(1));
//            }
//        }
//        catch(SQLException sql) {
//            System.out.println("Error in resultSet fill records");
//        }
//    }
//    public Vector getSelectedDLP(int _id) {
//        findSelectedDLP(_id);
//        return records;
//    }
    
    public Vector getLevelRecords() {
        findSecurityLevel();
        return records;
    }
    
    public Vector getThreatRecords() {
        findThreats();
        return records;
    }
    
    public void updateLevel(int _num, Vector _data) {
        String userQuery = "UPDATE enterprises "
                          + "name = ?, information = ?, adress = ? " 
                          +"where system_id = ?";
    }
    
}
