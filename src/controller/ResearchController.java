package controller;

import java.util.Map;
import model.Criterias;
import model.Estimates;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import model.DlpSystems;

class FuzzyElement {
    private float x;
    private float y;
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}

class FuzzyNumber {
    private final static int ELEMENTS = 5;
    private FuzzyElement[] numberSet = new FuzzyElement[ELEMENTS];
    public void setNumber(float number) {
        float countX = -2.0f;
        float countY = 0;
        for (int i = 0; i < ELEMENTS; i++) {
            numberSet[i].setX(number + countX);
            numberSet[i].setY(countY);
            if(i > 1) 
                countY--;
            else 
                countY++;
            countX++;
        }
    }
    public FuzzyElement[] getNumber() {
        return numberSet;
    }
}

public class ResearchController {
    private Criterias criteria = new Criterias();
    private DlpSystems dlpSystem = new DlpSystems();
    //private Vector criteriasEstimates = new Vector();
    private Vector dlpEstimatesByCriteria = new Vector();
    
    private List<Estimates> researchResultEstimates = new ArrayList();
    
    public void createResearch() {
        List<Integer> dlpId = dlpSystem.getResearchDlpId(1);
        for (Integer id :dlpId) {
            List<Estimates> dlpSystems = dlpSystem.getCompetenceEstimate(id);
            Vector resultVec = new Vector();
            float result = 0;
            for (Estimates estimate :dlpSystems) {
                int criteriaId = estimate.getCriteriaId();
                float criteriaFuzzy = criteria.getEstimatesFuzzyValue(criteriaId);
                float dlpFuzzy = estimate.getFuzzyEstimate();
                resultVec.add(dlpFuzzy*criteriaFuzzy);
            }
            for(int i = 0; i < resultVec.size(); i++) {
                result += (float)resultVec.get(i);
            }
            addResearchResult(id, result);
        }
    }
    public void printResults() {
        for(Estimates element :researchResultEstimates) 
            element.print();
    }
    private void addResearchResult(int dlpId, float result) {
        Estimates resultDlpEstimate = new Estimates();
        resultDlpEstimate.setDlpId(dlpId);
        resultDlpEstimate.setFuzzyEstimate(result);
        researchResultEstimates.add(resultDlpEstimate);
    }
    private void findCompetenceCriterias() {
        int id = 1;
        criteria.selectCompetenceCriterias(id);
    }
    private void findCompetenceSystems() {
        int research_id = 1;
        for (int system_id = 1; system_id < 7; system_id ++) 
          dlpSystem.selectCompetenceEstimates(system_id, research_id);
    }
    private List<Estimates> determinateFuzzyEstimate(CalculationEstimates calculationEstimates, 
                                                                 List<Estimates> listOfEstimates, 
                                                                 List<Estimates> competenceEstimates) {
        //listOfEstimates.clear();
        List<Estimates> currentEstimates = new ArrayList();
        float standartDeviation = calculationEstimates.getStdDev();
        float mean = calculationEstimates.getMean();
        float biggerVal = mean + standartDeviation;
        float smallerVal = mean-(standartDeviation/2);
        float lessVal = mean - standartDeviation;
        
        for (Estimates estimate :competenceEstimates) {
            float mark = 0.0f;
            float currVal = estimate.getMean();
            if (currVal > biggerVal)
                mark = 0.8f;
            else if (currVal < biggerVal && currVal > mean) 
                mark = 0.5f;
            else if (currVal < mean && currVal > smallerVal)
                mark = 0.35f;
            else if (currVal < smallerVal && currVal > lessVal)
                mark = 0.25f;
            else if (currVal < lessVal)
                mark = 0.2f;
            currentEstimates.add(setCurrentEstimates(estimate.getDlpId(), estimate.getCriteriaId(),  mark, findLinguisticEstimate(mark, listOfEstimates)));
       }
        return currentEstimates;
    }
    private Estimates setCurrentEstimates(int system_id, int criteria_id, float fuzzyMark, String lingMark) {
         Estimates currEstimate = new Estimates();
         currEstimate.setDlpId(system_id);
         currEstimate.setCriteriaId(criteria_id);
         currEstimate.setFuzzyEstimate(fuzzyMark);
         currEstimate.setLinguisticEstimate(lingMark);
         return currEstimate;
    }
    private String findLinguisticEstimate(float fuzzyMark, List <Estimates> listOfEstimates) {
        for (Estimates mark :listOfEstimates)
             if (mark.getFuzzyEstimate() == fuzzyMark)
                 return mark.getLinguisticEstimate();
        return null;
    }
    public Vector determineCriteriasEstimates() {
        Vector res = new Vector();
        findCompetenceCriterias();
        CalculationEstimates calculationCriteriaEstimates = new CalculationEstimates(criteria.getCompetenceEstimatesValue());
        List <Estimates> listOfEstimates = criteria.getEstimatesList();
        List<Estimates> competenceEstimates = criteria.getCompetenceEstimates();
        List<Estimates> currentEstimates = determinateFuzzyEstimate(calculationCriteriaEstimates, listOfEstimates, competenceEstimates);
        //System.out.println("---------------- Set criteria estimates. --------------------");
        for(Estimates estimate :currentEstimates) {
            criteria.setEstimatesFuzzyValue(estimate.getCriteriaId(), estimate.getFuzzyEstimate());
            Vector row = new Vector();
            row.add(estimate.getCriteriaId());
            row.add(estimate.getFuzzyEstimate());
            row.add(estimate.getLinguisticEstimate());
//            System.out.println("criteria id: " + estimate.getCriteriaId());
//            System.out.println("current fuzzy estimate: " + estimate.getFuzzyEstimate());
//            System.out.println("current ling estim: " + estimate.getLinguisticEstimate());
            res.add(row);
        }
        return res;
    }
    
    public /*Vector*/void determineDlpEstimates() {
        //Vector res = new Vector();
        findCompetenceSystems();
        List <Estimates> listOfEstimates = dlpSystem.getEstimateList();
        Vector competenceEstimates = dlpSystem.getCompetenceEstimates();
        for (int i = 0; i < competenceEstimates.size(); i++) {
            CalculationEstimates calculationSystemEstimates = new CalculationEstimates(dlpSystem.getCompetenceEstimateValue(i));    
            List <Estimates> competenceEstimatesByCriteria = (List<Estimates>) competenceEstimates.get(i);
            List<Estimates> currentEstimates = determinateFuzzyEstimate(calculationSystemEstimates, listOfEstimates, competenceEstimatesByCriteria);
            //System.out.println("------------------ Set dlp estimates. --------------------");
            for(Estimates estimate :currentEstimates) {
                dlpSystem.setEstimatesFuzzyValue(estimate.getDlpId(), estimate.getCriteriaId(), estimate.getFuzzyEstimate());
//                System.out.println("system id: " + estimate.getDlpId());
//                System.out.println("criteria id: " + estimate.getCriteriaId());
//                System.out.println("current fuzzy estimate: " + estimate.getFuzzyEstimate());
//                System.out.println("current ling estim: " + estimate.getLinguisticEstimate());
                dlpEstimatesByCriteria.add(estimate);
            }
        }
    }
    public Vector getDlpEstimates(int dlpId) {
        Vector res = new Vector();
        for (int i = 0; i <dlpEstimatesByCriteria.size(); i++) {
            Estimates element = (Estimates) dlpEstimatesByCriteria.get(i);
            if (element.getDlpId() == dlpId) {     
                Vector row = new Vector();
                row.add(element.getDlpId());
                row.add(element.getCriteriaId());
                row.add(element.getFuzzyEstimate());
                row.add(element.getLinguisticEstimate());
                res.add(row);
            }
        }
        return res;
    }
    
}
    