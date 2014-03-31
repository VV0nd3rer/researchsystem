
package model;

import java.util.Vector;

public class InputParameters extends ModelAction {
   
//    private Vector findRisks() {
//       sqlQuery = "SELECT * FROM risks" ;
//       records = findRecords(sqlQuery);
//       return records;
//    }
//    private Vector findCriterias() {
//       sqlQuery = "SELECT * FROM criterias;" ;
//       records = findRecords(sqlQuery);
//       return records;
//    }
//    private Vector findSystems() {
//       sqlQuery = "SELECT system_id,  FROM dlp_systems" ;
//       records = findRecords(sqlQuery);
//       return records;
//    }
    
    public Vector getInputParameters() {
        //Vector res = new Vector();        
        Vector<Vector> rows = new Vector<Vector>();
        String [] rowStr = {"Риски", "Критерии", "DLP-системы" };
        for (int i = 0; i < 3; i++)
        {
             Vector<String> rowVec = new Vector<String>();
             rowVec.add(rowStr[i]);
             rows.addElement(rowVec);
        }
        return rows;
    }
}
