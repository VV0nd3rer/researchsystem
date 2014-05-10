package model;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DlpSystems extends ModelAction {
    private Vector competenceEstimates = new Vector();
    private void fillCompetenceEstimate (ResultSet rs) {
        try {
            List<Estimates> competenceEstimateOfDlp = new ArrayList();
            while (rs.next()) {
                Estimates estimate = new Estimates();
                estimate.setDlpId(rs.getInt("system_id"));
                estimate.setCriteriaId(rs.getInt("criteria_id"));
                estimate.setMean(rs.getFloat("mean"));
                competenceEstimateOfDlp.add(estimate);
            }
            competenceEstimates.add(competenceEstimateOfDlp);
        }
        catch (SQLException ex)
        {
            System.out.println("Could not execute query, " + ex.toString());    
        }
    }
    private ResultSet findDlpEstimate(int systemId, int researchId, String sql) {
        PreparedStatement userStmt = null;
        ResultSet rs = null;
        try {
            userStmt = connection.prepareStatement(sql);
            userStmt.setInt(1, systemId);
            userStmt.setInt(2, researchId);
            rs = userStmt.executeQuery();
        }
        catch (SQLException ex)
        {
            System.out.println("Could not execute query, " + ex.toString());    
        }
        return rs;
    }
    public void selectCompetenceEstimates(int systemId, int researchId) {
        String sqlQuery = "SELECT * FROM research.competence_dlp_by_criteria where system_id = ? and research_id = ?";
        fillCompetenceEstimate(findDlpEstimate(systemId, researchId, sqlQuery));
   }
    private List<Estimates> findEstimateList(ResultSet rs) {
        List<Estimates> listOfEstimates = new ArrayList();    
        try {
            while (rs.next()) {     
               Estimates estimates = new Estimates();
               estimates.setCriteriaId(rs.getInt("estimate_id"));
               estimates.setFuzzyEstimate(rs.getFloat("fuzzy_value"));
               estimates.setLinguisticEstimate(rs.getString("linguistic_value"));
               listOfEstimates.add(estimates);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Could not execute query, " + ex.toString());    
        }
        return listOfEstimates;
    }
    public List<Estimates> getEstimateList() {
        String sqlQuery = "SELECT * FROM dlp_estimate";
        return findEstimateList(findRecords(sqlQuery));
    }
    public List<Float> getCompetenceEstimateValue(int index) {
        List<Float> res = new ArrayList();
        for (int i = 0; i < competenceEstimates.size(); i++) 
        {
            if (i == index)
            {
                List<Estimates> element = (List<Estimates>) competenceEstimates.get(i);
                for(Estimates estimates :element) {
                    res.add(estimates.getMean());
                }
            }
        }
        return res;
    }
    public Vector getCompetenceEstimates() {
        return competenceEstimates;
    }
    public Vector getResearchDlp(int researchId) {
        Vector res = new Vector();
        String sqlQuery = "Select * from research_dlp, dlp_systems where research_dlp.research_id = ? and research_dlp.system_id = dlp_systems.system_id";
        ResultSet rs = findRecordsById(sqlQuery, researchId);
        try {
            
            while(rs.next()) {
                Vector row = new Vector();
                row.add(rs.getInt("system_id"));
                row.add(rs.getString("title"));
                row.add(rs.getString("information"));
                res.add(row);
            }
            
        }
        catch(SQLException sql) {
            
        }
        return res;
    }
}
