package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.List;

// class CompetenceCriterias {
//    private int competenceCriteriaId;
//    //The mean estimate of all experts estimates
//    private float competenceCriteriaMean;
//    public void setCriteriaId(int id) {
//        competenceCriteriaId = id;
//    }
//    public int getCriteriaId() {
//        return competenceCriteriaId;
//    }
//    public void setMean(float mean) {
//        competenceCriteriaMean = mean;
//    }
//    public float getMean() {
//        return competenceCriteriaMean;
//    }
//    public void print() {
//        System.out.println(competenceCriteriaId);
//        System.out.println(competenceCriteriaMean);
//    }
//}

public class Criterias extends ModelAction {
    //private Vector competenceCriteriasVec = new Vector<CompetenceCriterias>();
    private Map<Integer, Float> competenceEstimateMap = new HashMap<Integer, Float>();
//    private float competentEstimate;
//    private float consistency;
    
    
//    public void setCriteriaId(int id) {
//        criteriaId = id;
//    }
    
//    public void setCriteriaTitle(String title) {
//        criteriaTitle = title;
//    }
    
    private void findCompetenceCriterias(String sqlQuery, int researchId) {
        PreparedStatement userStmt = null;
        ResultSet rs = null;
        try {
            userStmt = connection.prepareStatement(sqlQuery);
            userStmt.setInt(1, researchId);      
            //sm = connection.createStatement();
            rs = userStmt.executeQuery();
            while (rs.next()) {
//                CompetenceCriterias _competenceCriterias = new CompetenceCriterias();
//                _competenceCriterias.setCriteriaId(rs.getInt("criteria_id"));
//                //criteriaTitle = rs.getString("criteria");
//                _competenceCriterias.setMean(rs.getFloat("mean"));
                competenceEstimateMap.put(rs.getInt("criteria_id"),rs.getFloat("mean"));
                System.out.println("criteria id: " + rs.getInt("criteria_id"));
                System.out.println("mean: " + rs.getFloat("mean"));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Could not execute query, " + ex.toString());    
        }
    }
    private List<Estimates> findEstimateList(String sqlQuery) {
        List<Estimates> listOfEstimates = new ArrayList();
        PreparedStatement userStmt = null;
        ResultSet rs = null;
        try {
            userStmt = connection.prepareStatement(sqlQuery);
            rs = userStmt.executeQuery();
            while (rs.next()) {     
               Estimates estimates = new Estimates();
               estimates.setId(rs.getInt("estimate_id"));
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
    public void selectCompetenceCriterias(int researchId) {
        String sqlQuery = "SELECT * FROM competence_criterias where research_id = ?";
        findCompetenceCriterias(sqlQuery, researchId);
        //fillCompetenceCriteriasMap();
    }
    public List<Estimates> getEstimatesList() {
        String sqlQuery = "SELECT * FROM criterias_estimate";
        return findEstimateList(sqlQuery);     
    }
    public void printCompetenceCriterias() {
        for(Map.Entry<Integer, Float> entry : competenceEstimateMap.entrySet()) {
            System.out.println("competenceCriteriasMap: " + entry.getKey());
            System.out.println("competenceCriteriasValue: " + entry.getValue());
        }
    }
    public List<Float> getCompetenceEstimatesValue() {
        List<Float> data = new ArrayList();
        for(Map.Entry<Integer, Float> entry : competenceEstimateMap.entrySet()) 
           data.add(entry.getValue());
        return data;
    }
    public Map<Integer, Float> getCompetenceEstimates() {
        return competenceEstimateMap;
    }
//    private void fillCompetenceCriteriasMap() {
//        for (int i = 0; i < competenceCriteriasVec.size(); i++) {
//            CompetenceCriterias criteria = new CompetenceCriterias();
//            criteria = (CompetenceCriterias)competenceCriteriasVec.get(i);
//            competenceCriteriasMap.put(criteria.getCriteriaId(), criteria.getMean());
//        }
//    }
   
}
