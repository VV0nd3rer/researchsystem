package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.List;

public class Criterias extends ModelAction {
    private List<Estimates> competenceEstimate = new ArrayList();
   
    private void fillCompetenceCriterias(ResultSet rs) {
        try {
            competenceEstimate.clear();
            while (rs.next()) {
                Estimates estimate = new Estimates();
                estimate.setCriteriaId(rs.getInt("criteria_id"));
                estimate.setMean(rs.getFloat("mean"));
                competenceEstimate.add(estimate);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Could not execute query, " + ex.toString());    
        }
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
    public void selectCompetenceCriterias(int researchId) {
        String sqlQuery = "SELECT * FROM competence_criterias where research_id = ?";
        ResultSet rs = findRecordsById(sqlQuery, researchId);
        fillCompetenceCriterias(rs);
        //findCompetenceCriterias(sqlQuery, researchId);
    }
    public List<Estimates> getEstimatesList() {
        String sqlQuery = "SELECT * FROM criterias_estimate";
        return findEstimateList(findRecords(sqlQuery));     
    }
    public List<Float> getCompetenceEstimatesValue() {
        List<Float> data = new ArrayList();
        for(Estimates estimate :competenceEstimate) 
           data.add(estimate.getMean());
        return data;
    }
    public List<Estimates> getCompetenceEstimates() {
//        System.out.println("New call of getCompetenceEstimate");
//        for(Estimates estim:competenceEstimate) {
//            estim.print();
//        }
        return competenceEstimate;
    }
    public float getEstimatesFuzzyValue(int criteriaId) {
        for (Estimates estimate :competenceEstimate) {
            if(estimate.getCriteriaId() == criteriaId)
               return estimate.getFuzzyEstimate();
        }
        return 0;
    }
    public void setEstimatesFuzzyValue(int criteriaId, float value) {
        for (Estimates estimate :competenceEstimate) {
            if(estimate.getCriteriaId() == criteriaId) {
                estimate.setFuzzyEstimate(value);
            }
        }
    }
}
