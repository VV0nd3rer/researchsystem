package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Enterprises extends ModelAction {
      
    private Vector findEnterprises() {
        sqlQuery = "SELECT * FROM enterprises";
        records = findRecords(sqlQuery);
        return records;
    }
    
    private Vector findAudits() {
        sqlQuery = "SELECT audit_id, enterprises.name, security_level.level FROM research.audits, enterprises, security_level " +
                        "where audits.enterprise_id = enterprises.enterprise_id and " +
                        "audits.level_id = security_level.level_id";
        records = findRecords(sqlQuery);
        return records;
    }
    private Vector findResearches() {
        sqlQuery = "SELECT research_id, name, title, is_done  FROM researches, enterprises, dlp_systems " +
                        "where researches.enterprise_id = enterprises.enterprise_id and " +
                        "researches.system_id = dlp_systems.system_id";
        records = findRecords(sqlQuery);
        return records;
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
    public void createAudit(Vector _data) {
        String userQuery = "INSERT INTO audits (enterprise_id, level_id) VALUES "
                            +"(?,?)";
        createForeignRecord(_data, userQuery);
    }
    public Vector getAudits() {
        return findAudits();
    }
    public Vector getResearches() {
        return findResearches();
    }
    public Vector getEnterprises() {
        return findEnterprises();
    }
    
}
