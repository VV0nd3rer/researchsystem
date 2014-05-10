package controller;

import java.util.Map;
import model.Criterias;
import model.Estimates;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import model.DlpSystems;

public class ResearchController {
    private Criterias criteria = new Criterias();
    private DlpSystems dlpSystem = new DlpSystems();
    private Vector criteriasEstimates = new Vector();
    private Vector dlpEstimates = new Vector();
    //private CalculationEstimates calculationEstimates;
    //private List<Estimates> listOfEstimates = new ArrayList();
    //private List<Estimates> currentEstimates = new ArrayList();
    //Coefficients for set fuzzy estimates
    //List<Float> appropriateCoefficients = new ArrayList();
    
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
//    private void determinateFuzzyEstimate() {
//        findCompetenceCriterias();
//        calculationEstimates = new CalculationEstimates(criteria.getCompetenceEstimatesValue());
//        float standartDeviation = calculationEstimates.getStdDev();
//        float mean = calculationEstimates.getMean();
//        float biggerVal = mean + standartDeviation;
//        float smallerVal = mean-(standartDeviation/2);
//        float lessVal = mean - standartDeviation;
//        Map <Integer, Float> competenceEstimates = criteria.getCompetenceEstimates();
//        listOfEstimates = criteria.getEstimatesList();
//        for (Map.Entry<Integer, Float> entry: competenceEstimates.entrySet()) {
//            float mark = 0.0f;
//            float currVal = entry.getValue();
//            if (currVal > biggerVal)
//                mark = 0.8f;
//            else if (currVal < biggerVal && currVal > mean) 
//                mark = 0.5f;
//            else if (currVal < mean && currVal > smallerVal)
//                mark = 0.35f;
//            else if (currVal < smallerVal && currVal > lessVal)
//                mark = 0.25f;
//            else if (currVal < lessVal)
//                mark = 0.2f;
//            setCurrentEstimates(entry.getKey(), mark, findLinguisticEstimate(mark));
//        }
////        appropriateCoefficients.add(biggerVal);
////        appropriateCoefficients.add(mean);
////        appropriateCoefficients.add(smallerVal);
////        appropriateCoefficients.add(lessVal);        
//    }
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
//    public Vector setCriteriasEstimates() {
//        Vector res = new Vector();
//        determinateFuzzyEstimate();
//        for(Estimates estimate :currentEstimates) {
//            Vector row = new Vector();
//            row.add(estimate.getCriteriaId());
//            row.add(estimate.getFuzzyEstimate());
//            row.add(estimate.getLinguisticEstimate());
//            System.out.println("criteria id: " + estimate.getCriteriaId());
//            System.out.println("current fuzzy estimate: " + estimate.getFuzzyEstimate());
//            System.out.println("current ling estim: " + estimate.getLinguisticEstimate());
//            res.add(row);
//        }
//        return res;
//    }
    public Vector determineCriteriasEstimates() {
        Vector res = new Vector();
        findCompetenceCriterias();
        CalculationEstimates calculationCriteriaEstimates = new CalculationEstimates(criteria.getCompetenceEstimatesValue());
        List <Estimates> listOfEstimates = criteria.getEstimatesList();
        List<Estimates> competenceEstimates = criteria.getCompetenceEstimates();
        List<Estimates> currentEstimates = determinateFuzzyEstimate(calculationCriteriaEstimates, listOfEstimates, competenceEstimates);
        System.out.println("---------------- Set criteria estimates. --------------------");
        for(Estimates estimate :currentEstimates) {
            Vector row = new Vector();
            row.add(estimate.getCriteriaId());
            row.add(estimate.getFuzzyEstimate());
            row.add(estimate.getLinguisticEstimate());
            System.out.println("criteria id: " + estimate.getCriteriaId());
            System.out.println("current fuzzy estimate: " + estimate.getFuzzyEstimate());
            System.out.println("current ling estim: " + estimate.getLinguisticEstimate());
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
            System.out.println("------------------ Set dlp estimates. --------------------");
            for(Estimates estimate :currentEstimates) {
//                Vector row = new Vector();
//                row.add(estimate.getDlpId());
//                row.add(estimate.getCriteriaId());
//                row.add(estimate.getFuzzyEstimate());
//                row.add(estimate.getLinguisticEstimate());
                System.out.println("system id: " + estimate.getDlpId());
                System.out.println("criteria id: " + estimate.getCriteriaId());
                System.out.println("current fuzzy estimate: " + estimate.getFuzzyEstimate());
                System.out.println("current ling estim: " + estimate.getLinguisticEstimate());
                //res.add(row);
                dlpEstimates.add(estimate);
            }
        }
       //return res;
    }
    public Vector getDlpEstimates(int dlpId) {
        Vector res = new Vector();
        for (int i = 0; i <dlpEstimates.size(); i++) {
            Estimates element = (Estimates) dlpEstimates.get(i);
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
    