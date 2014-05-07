package controller;

import java.util.Map;
import model.Criterias;
import model.Estimates;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

public class ResearchController {
    private Criterias criteria = new Criterias();
    private CalculationEstimates calculationEstimates;
    private List<Estimates> listOfEstimates = new ArrayList();
    private List<Estimates> currentEstimates = new ArrayList();
    //Coefficients for set fuzzy estimates
    //List<Float> appropriateCoefficients = new ArrayList();
    
    private void findCompetenceCriterias() {
        int id = 1;
        criteria.selectCompetenceCriterias(id);
    }
    private void determinateFuzzyEstimate() {
        findCompetenceCriterias();
        calculationEstimates = new CalculationEstimates(criteria.getCompetenceEstimatesValue());
        float standartDeviation = calculationEstimates.getStdDev();
        float mean = calculationEstimates.getMean();
        float biggerVal = mean + standartDeviation;
        float smallerVal = mean-(standartDeviation/2);
        float lessVal = mean - standartDeviation;
        Map <Integer, Float> competenceEstimates = criteria.getCompetenceEstimates();
        listOfEstimates = criteria.getEstimatesList();
        for (Map.Entry<Integer, Float> entry: competenceEstimates.entrySet()) {
            float mark = 0.0f;
            float currVal = entry.getValue();
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
            setCurrentEstimates(entry.getKey(), mark, findLinguisticEstimate(mark));
        }
//        appropriateCoefficients.add(biggerVal);
//        appropriateCoefficients.add(mean);
//        appropriateCoefficients.add(smallerVal);
//        appropriateCoefficients.add(lessVal);        
    }
    private void setCurrentEstimates(int id, float fuzzyMark, String lingMark) {
         Estimates currEstimate = new Estimates();
         currEstimate.setId(id);
         currEstimate.setFuzzyEstimate(fuzzyMark);
         currEstimate.setLinguisticEstimate(lingMark);
         currentEstimates.add(currEstimate);
    }
    private String findLinguisticEstimate(float fuzzyMark) {
        for (Estimates mark :listOfEstimates)
             if (mark.getFuzzyEstimate() == fuzzyMark)
                 return mark.getLinguisticEstimate();
        return null;
    }
    public Vector setCriteriasEstimates() {
        Vector res = new Vector();
        determinateFuzzyEstimate();
        for(Estimates estimate :currentEstimates) {
            Vector row = new Vector();
            row.add(estimate.getId());
            row.add(estimate.getFuzzyEstimate());
            row.add(estimate.getLinguisticEstimate());
            System.out.println("criteria id: " + estimate.getId());
            System.out.println("current fuzzy estimate: " + estimate.getFuzzyEstimate());
            System.out.println("current ling estim: " + estimate.getLinguisticEstimate());
            res.add(row);
        }
        return res;
    }
}
    