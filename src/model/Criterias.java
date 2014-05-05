package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

 class CompetenceCriterias {
    private int competenceCriteriaId;
    private float competenceCriteriaMean;
    public void setCriteriaId(int id) {
        competenceCriteriaId = id;
    }
    public int getCriteriaId() {
        return competenceCriteriaId;
    }
    public void setMean(float mean) {
        competenceCriteriaMean = mean;
    }
    public float getMean() {
        return competenceCriteriaMean;
    }
    public void print() {
        System.out.println(competenceCriteriaId);
        System.out.println(competenceCriteriaMean);
    }
}

public class Criterias extends ModelAction {
    private Vector competenceCriteriasVec = new Vector<CompetenceCriterias>();
    public Map<Integer, Float> competenceCriteriasMap = new HashMap<Integer, Float>();
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
                CompetenceCriterias _competenceCriterias = new CompetenceCriterias();
                _competenceCriterias.setCriteriaId(rs.getInt("criteria_id"));
                //criteriaTitle = rs.getString("criteria");
                _competenceCriterias.setMean(rs.getFloat("mean"));
                competenceCriteriasVec.add(_competenceCriterias);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Could not execute query, " + ex.toString());    
        }
    }
    public void selectCompetenceCriterias(int researchId) {
        String sqlQuery = "SELECT * FROM competence_criterias where research_id = ?";
        findCompetenceCriterias(sqlQuery, researchId);
        fillCompetenceCriteriasMap();
    }
    public void printCompetenceCriterias() {
        for (int i = 0; i < competenceCriteriasVec.size(); i++) {
            CompetenceCriterias criteria = new CompetenceCriterias();
            criteria = (CompetenceCriterias)competenceCriteriasVec.get(i);
            criteria.print();
        }
    }
    private void fillCompetenceCriteriasMap() {
        for (int i = 0; i < competenceCriteriasVec.size(); i++) {
            CompetenceCriterias criteria = new CompetenceCriterias();
            criteria = (CompetenceCriterias)competenceCriteriasVec.get(i);
            competenceCriteriasMap.put(criteria.getCriteriaId(), criteria.getMean());
        }
    }
   
}
