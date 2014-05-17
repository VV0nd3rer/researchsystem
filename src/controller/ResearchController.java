package controller;

import java.util.Map;
import model.Criterias;
import model.Estimates;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
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
    public final static int ELEMENTS = 5;
    private int dlpId;
    private int criteriaId;
    private String dlpTitle;
    private String criteriaTitle;
    private float fuzzyNumber;
    private FuzzyElement[] numberSet = new FuzzyElement[ELEMENTS];
    public void setNumber(float number) {
        for (int i = 0; i < ELEMENTS; i++) {
            numberSet[i] = new FuzzyElement();
        }
        setX(number);
        setY();
    }
    private void setY() {
        float countY = 0;
        for (int i = 0; i < ELEMENTS; i++) {
            numberSet[i].setY(countY);
            if(i > 1) 
                countY -= 0.5;
            else 
                countY += 0.5;
        }
    }
    private void setX(float number) {
        float countX = -0.2f;
         for (int i = 0; i < ELEMENTS; i++) {
             numberSet[i].setX(number + countX);
             countX+=0.1;
         }
    }
    public void setNumber(FuzzyElement[] numberSet) {
        this.numberSet = numberSet;
        setY();
    }
    public void setNumber(float[] numX) {
        for (int i = 0; i < numX.length; i++) {
            numberSet[i] = new FuzzyElement();
            numberSet[i].setX(numX[i]);
        }
        setY();
    }
    public FuzzyElement[] getNumber() {
        return numberSet;
    }
    public void setDlpId(int dlpId) {
        this.dlpId = dlpId;
    }
    public int getDlpId() {
        return dlpId;
    }
    public void setDlpTitle(String title) {
        dlpTitle = title;
    }
    public String getDlpTitle() {
        return dlpTitle;
    }
    public void setCriteriaId(int criteriaId) {
        this.criteriaId = criteriaId;
    }
    public int getCriteriaId() {
        return criteriaId;
    }
    public boolean thisCriteria(int id) {
        if(criteriaId == id) 
            return true;
        else 
            return false;
    }
    public float getFuzzyNumber() {
        return this.fuzzyNumber;
    }
    public void setFuzzyNumber(float num) {
        fuzzyNumber = num;
    }
    public void print() {
        System.out.println("criteriaId: " + criteriaId);
        System.out.println("dlpId: " + dlpId);
        System.out.println("fuzzyNum: " + fuzzyNumber);
        for(FuzzyElement element : numberSet) {
            System.out.println("x: " + element.getX());
            System.out.println("y: " + element.getY());
        }
    }
}

public class ResearchController {
    private Criterias criteria = new Criterias();
    private DlpSystems dlpSystem = new DlpSystems();
    private DrawGraph drawing = new DrawGraph();
    //private Vector criteriasEstimates = new Vector();
    private Vector dlpEstimatesByCriteria = new Vector();
    //private List<Estimates> researchResultEstimates = new ArrayList();
    private List<FuzzyNumber> criteriaResult = new ArrayList();
    private List<FuzzyNumber> dlpResult = new ArrayList();
    private List<FuzzyNumber> fuzzyResult = new ArrayList();
    
    public void createResearch() {
        fillResultsLists(dlpSystem.getResearchDlpId(1));
        fillFuzzyList();
//            for(int i = 0; i < resultVec.size(); i++) {
//                result += (float)resultVec.get(i);
//            }
//            addResearchResult(id, result);
//        }
    }
    private void fillResultsLists(List<Integer> dlpId) {
            List<Estimates> criterias = criteria.getCompetenceEstimates();
            for (Estimates criteriaEstimate : criterias) {
                 FuzzyNumber criteriaNumber = new FuzzyNumber();
                 int criteriaId = criteriaEstimate.getCriteriaId();
                 float criteriaFuzzy = criteria.getEstimatesFuzzyValue(criteriaId);
                criteriaNumber.setCriteriaId(criteriaId);
                criteriaNumber.setNumber(criteriaFuzzy);
                criteriaResult.add(criteriaNumber);
            }
          for (Integer id :dlpId) {
            List<Estimates> dlpSystems = dlpSystem.getCompetenceEstimate(id);
            //Vector resultVec = new Vector();
            for (Estimates dlpEstimate :dlpSystems) {
                int criteriaId = dlpEstimate.getCriteriaId();
                float dlpFuzzy = dlpEstimate.getFuzzyEstimate();
                FuzzyNumber dlpNumber = new FuzzyNumber();             
                dlpNumber.setCriteriaId(criteriaId);
                dlpNumber.setDlpId(dlpEstimate.getDlpId());
                dlpNumber.setNumber(dlpFuzzy);  
                dlpResult.add(dlpNumber);
                //resultVec.add(dlpFuzzy*criteriaFuzzy);
            }
         }
    }
    private void fillFuzzyList() {
         int dlpId = dlpResult.get(0).getDlpId();
        ArrayList<FuzzyNumber> multiplyNum = new ArrayList();
        for (FuzzyNumber dlpNum : dlpResult) {
//            dlpResult.get(dlpResult.size()-1).print();
             if (dlpId != dlpNum.getDlpId() || 
                (dlpNum.getCriteriaId() == dlpResult.get(dlpResult.size()-1).getCriteriaId() && 
                 dlpNum.getDlpId() == dlpResult.get(dlpResult.size()-1).getDlpId())) {
//                 System.out.println("New DLP");
                 float[] numSet = new float[]  {0,0,0,0,0};
                for (FuzzyNumber num : multiplyNum) {
                     FuzzyElement[] current = num.getNumber();
                     for(int i = 0; i < current.length; i++) {
//                         System.out.println("multiplyNum's element X: " + current[i].getX());
                         numSet[i] += current[i].getX();
                     }
                 }
//                System.out.println("numSet: ");
//                for(int i = 0; i < numSet.length; i++) {
//                    System.out.println(numSet[i]);
//                }
                
                 FuzzyNumber result = new FuzzyNumber();
                 result.setNumber(numSet);
                 result.setDlpId(dlpId);
                 result.setFuzzyNumber(numSet[2]);
                 result.setDlpTitle(dlpSystem.getResearchDlpTitle(dlpId));
                 fuzzyResult.add(result);
                 dlpId = dlpNum.getDlpId();
                 multiplyNum.clear();
             }
           
             int criteriaId = dlpNum.getCriteriaId();
             for (FuzzyNumber criteriaNum : criteriaResult) {
                 if(criteriaNum.thisCriteria(criteriaId))
                     multiplyNum.add(multiplyNumbers(criteriaNum.getNumber(), dlpNum.getNumber()));
             }

        }
    }
    public FuzzyNumber multiplyNumbers(FuzzyElement[] criteria, FuzzyElement[] dlp) {
            FuzzyNumber result = new FuzzyNumber();      
            FuzzyElement[] resultElem = new FuzzyElement[5];
            for (int i = 0; i < dlp.length; i++) {
                resultElem[i] = new FuzzyElement();
                resultElem[i].setX(criteria[i].getX() * dlp[i].getX());
            }
            result.setNumber(resultElem);
            return result;
    }
    public void drawGraph() {
//        for (FuzzyNumber element : fuzzyResult) {
//            FuzzyNumber fuzzyNumber = new FuzzyNumber();
//            fuzzyNumber.setTitle(dlpSystem.getResearchDlpTitle(estimate.getDlpId()));
//            fuzzyNumber.setNumber(estimate.getFuzzyEstimate());
//            fuzzyResult.add(fuzzyNumber);
//        }
        drawing.Draw(/*points*/fuzzyResult);
        
    }
    /*For debug */
    public void printResults() {
        printElement(criteriaResult, "criteriaResult");
        printElement(dlpResult, "dlpResult");
        printElement(fuzzyResult, "fuzzyResult");
    }
    private void printElement(List<FuzzyNumber> element, String param) {
        for(FuzzyNumber elem :element) { 
            System.out.println(param);
            elem.print();
        }
    }
    /*------*/
    public Vector getResults() {
        Vector res = new Vector();
        for(FuzzyNumber element :fuzzyResult) {
            Vector row = new Vector();
            int id = element.getDlpId();
            row.add(id);
            row.add(dlpSystem.getResearchDlpTitle(id));
            row.add(element.getFuzzyNumber());
            res.add(row);
        }
        return res;
    }
    private void addResearchResult(int dlpId, float result) {
//        Estimates resultDlpEstimate = new Estimates();
//        resultDlpEstimate.setDlpId(dlpId);
//        resultDlpEstimate.setFuzzyEstimate(result);
//        researchResultEstimates.add(resultDlpEstimate);
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
     private List<Estimates> determinateFuzzyCriteria(CalculationEstimates calculationEstimates, 
                                                                 List<Estimates> listOfEstimates, 
                                                                 List<Estimates> competenceEstimates) {
        
//        float maxVal = calculationEstimates.getMaxValue();
//        float minVal = calculationEstimates.getMinValue();
//        float averageVal = (maxVal + minVal)/2;
//        
//        float biggerVal = (maxVal + averageVal)/2;
//        //mean == averageVal
//        float smallerVal = (minVal + averageVal)/2;
//        float lessVal = (minVal + smallerVal)/2;
        
        float standartDeviation = calculationEstimates.getStdDev();
        float mean = calculationEstimates.getMean();
        float biggerVal = mean + standartDeviation;
        float smallerVal = mean-(standartDeviation/2);
        float lessVal = mean - standartDeviation;
       
        return determineFuzzyMark(competenceEstimates, listOfEstimates, 
                                                 biggerVal, mean, smallerVal, lessVal);
       
    }
    private List<Estimates> determinateFuzzyDlp(CalculationEstimates calculationEstimates, 
                                                                 List<Estimates> listOfEstimates, 
                                                                 List<Estimates> competenceEstimates) {

        float maxVal = calculationEstimates.getMaxValue();
        float minVal = calculationEstimates.getMinValue();
        float averageVal = (maxVal + minVal)/2;
        
        float biggerVal = (maxVal + averageVal)/2;
        //mean == averageVal
        float smallerVal = (minVal + averageVal)/2;
        float lessVal = (minVal + smallerVal)/2;
        
//        float standartDeviation = calculationEstimates.getStdDev();
//        float mean = calculationEstimates.getMean();
//        float biggerVal = mean + standartDeviation;
//        float smallerVal = mean-(standartDeviation/2);
//        float lessVal = mean - standartDeviation;
        
          return determineFuzzyMark(competenceEstimates, listOfEstimates, 
                                                 biggerVal, averageVal, smallerVal, lessVal);
    }
    private List<Estimates> determineFuzzyMark(List<Estimates> estimates, List<Estimates> listOfEstimates, 
                                                                        float biggerVal, float averageVal, float smallerVal, float lessVal) {
         List<Estimates> currentEstimates = new ArrayList();
         for (Estimates estimate :estimates) {
            float mark = 0.0f;
            float currVal = estimate.getMean();
            if (currVal > biggerVal)
                mark = 0.8f;
            else if (/*currVal < biggerVal && */ currVal > averageVal) 
                mark = 0.5f;
            else if (/*currVal < averageVal && */currVal > smallerVal)
                mark = 0.35f;
            else if (/*currVal < smallerVal && */currVal > lessVal)
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
        List<Estimates> currentEstimates = determinateFuzzyCriteria(calculationCriteriaEstimates, listOfEstimates, competenceEstimates);
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
        
        Vector commonData = new Vector();
        for (int i = 0; i < competenceEstimates.size(); i++) {
            List<Float> res = dlpSystem.getCompetenceEstimateValue(i);
            commonData.add(res);
        }
        CalculationEstimates calculationSystemEstimates = new CalculationEstimates((Object)commonData);
        
        for (int i = 0; i < competenceEstimates.size(); i++) {
            //CalculationEstimates calculationSystemEstimates = new CalculationEstimates(dlpSystem.getCompetenceEstimateValue(i));    
            List <Estimates> competenceEstimatesByCriteria = (List<Estimates>) competenceEstimates.get(i);
            List<Estimates> currentEstimates = determinateFuzzyDlp(calculationSystemEstimates, listOfEstimates, competenceEstimatesByCriteria);

            for(Estimates estimate :currentEstimates) {
                dlpSystem.setEstimatesFuzzyValue(estimate.getDlpId(), estimate.getCriteriaId(), estimate.getFuzzyEstimate());                
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
    