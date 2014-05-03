package controller;
import java.util.Vector;
import javax.swing.JTable;
import java.util.*;

class ThreatGroup {
    private static final int KOEF = 100;
    private int levelId;
    private int threatId;
    private float percent;
    private float factor;

    public ThreatGroup(int threatId, int levelId) {
        this.threatId = threatId;
        this.levelId = levelId;
        this.percent = 10.0f;
    }
    public ThreatGroup(int threatId, int levelId, float factor) {
        this.threatId = threatId;
        this.levelId = levelId;
        this.factor = factor;
    }
     public ThreatGroup() { 
     }
     public int getLevelId() {
         return levelId;
     }
     public int getThreatId() {
         return threatId;
     }
     public void setPercent(float percent) {
         this.percent = percent;
         factor = percent/KOEF;
     }
     public float getPercent() {
         return percent;
     }
     public float getFactor() {
         return factor;
     }
     public void setFactor(float factor) {
         this.factor = factor;
     }
     /*---------- Debbuging method --------*/
     public void printParam() {
         System.out.println("threat id: " + threatId);
         System.out.println("level id: " + levelId);
         System.out.println("Factor: " + factor);
     }
     /*--------------------------*/
}

public class SecurityLevelController {
    private Vector levelId = new Vector();
    private Vector threatId = new Vector();
    
    private Vector<ThreatGroup> threatGroup = new Vector<ThreatGroup>();
    private Vector<ThreatGroup> resultStatistic = new Vector<ThreatGroup>();
    
    private int selectedLevel;
    private int findLevel;
    private Map<Integer, Float> commonLevel = new HashMap<Integer, Float>();
    private Map<Integer, Boolean> pentestResult = new HashMap<Integer, Boolean>();
    
    //General statistics about the level of information system security
    public void setCommonPercent(Integer num, Float percent) {
        commonLevel.put(num, percent/100);
        for(Map.Entry<Integer, Float> entry : commonLevel.entrySet()) {
//            System.out.println("commonLevel key: " + entry.getKey());
//            System.out.println("commonLevel value: " + entry.getValue());
        }
    }
    public void createThreatList() {
        createList(threatGroup);
    }
//    public void createResultList() {
//        createList(resultStatistic);
//    }
    private void createList(Vector data) {
        data.clear();
        for (int i = 0; i < threatId.size(); i++) {
            for (int j = 0; j < levelId.size(); j++) {
               data.add(new ThreatGroup((int)threatId.get(i), (int)levelId.get(j)));
            }           
        }
    }
    public void setThreatPercent(Integer num, Float percent) {
       for (int i = 0; i < threatGroup.size(); i++) {
           ThreatGroup group = new ThreatGroup();
           group = (ThreatGroup)threatGroup.get(i);
           if (group.getLevelId() == selectedLevel) {
               if (num.equals(group.getThreatId())) {
                   group.setPercent(percent);
                   //System.out.println(group.getPercent());
               }
           }
        }
    }
    public void setResultStatistic() {
        calculateResult();
        findConfidence();
    }
    public Vector getResultStatistic() {
        Vector res = new Vector();
        Vector factor = new Vector();
        int count = 0;
        for (Map.Entry<Integer, Float> entry: commonLevel.entrySet()) {
                  factor.add(entry.getValue());
        }
        res.addElement(factor);
        factor = new Vector();
        for (int i = 0; i < resultStatistic.size(); i++) {
            ThreatGroup group = (ThreatGroup)resultStatistic.get(i);
             factor.add((Float)group.getFactor());
            if (count == 2) {
                res.addElement(factor);
//                System.out.println("factor: " + factor);
//                System.out.println("res: " + res);
                factor = new Vector();
                //System.out.println("res: " + res);
                count = 0;
            }
            else 
                count++;
        }
        //System.out.println(res);
        return res;
    }
    private void calculateResult() {
       
        int previousThreatId = -1;
        int currentThreatId = -1;
        float prevPercent = 0;
        boolean isImplement = false;
        resultStatistic.clear();
        
        for (int i = 0; i < threatGroup.size(); i++) {
            ThreatGroup group = (ThreatGroup)threatGroup.get(i);
//            for (Map.Entry<Integer, Boolean> entry : pentestResult.entrySet()) {
//                
//            }
            Vector prevPercentsVec = new Vector();
            int levelNum = group.getLevelId();
            //System.out.println("levelNum: " + levelNum);
            //Next row of threats
            if (currentThreatId != group.getThreatId() && currentThreatId > 0) {
                previousThreatId = currentThreatId;
                currentThreatId = group.getThreatId();
            }
            else if (currentThreatId < 0) {
                currentThreatId = group.getThreatId();  
            }
//            System.out.println("---------------------");
//            System.out.println("getThreatId: " + group.getThreatId());
           //System.out.println("currThId: " + currentThreatId);
//            System.out.println("prevThId: " + previousThreatId);
//            System.out.println("--------------------");
            
            for (Map.Entry<Integer, Boolean> entry : pentestResult.entrySet()) {
               if(group.getThreatId() == entry.getKey()) {
                   //group.printParam();
                   if(entry.getValue() == true)
                       isImplement = true;
                   else 
                       isImplement = false;
               }
            }
            
            if (previousThreatId < 0) {
                prevPercent = commonLevel.get(levelNum);
                for (Map.Entry<Integer, Float> entry: commonLevel.entrySet()) {
                  prevPercentsVec.add(entry.getValue());
                }
            }
            else /*if (currentThreatId == previousThreatId || previousThreatId > 0)*/ {
                prevPercent = findFactor(resultStatistic, previousThreatId, levelNum);
                prevPercentsVec = findElements(resultStatistic, previousThreatId);
            }
            //System.out.println("calculateResult prevPercent: " + prevPercent);
            //System.out.println("calculateResult group.getFactor(): " + group.getFactor());
            float numerator = calculateNumerator(prevPercent, group.getFactor(), isImplement);
            //System.out.println("calculateResult numerator: " + numerator);
            
            //System.out.println("calculateResult prevPercentsVec: " + prevPercentsVec);
            //System.out.println("calculateResult findElements(threatGroup, currentThreatId): " + findElements(threatGroup, currentThreatId));
            float denominator = calculateDenominator(prevPercentsVec, findElements(threatGroup, currentThreatId));
            //System.out.println("calculateResult denominator: " + denominator);
            float res = numerator/denominator;
            //System.out.println("calculateResult res: " + res);
            resultStatistic.add(new ThreatGroup(currentThreatId, levelNum, res));     
        }
//         System.out.println("-------------------- Printing resultStatistic vector -----------------------");
//            for (int j = 0; j < resultStatistic.size(); j++) {
//                ThreatGroup el = new ThreatGroup();
//                el = (ThreatGroup)resultStatistic.get(j);
//                System.out.println("-------------------------");
//                el.printParam();
//                System.out.println("-------------------------");
//            }
//            
    }
    private float calculateDenominator(Vector v1, Vector v2) {
        float res = 0;
        for (int i = 0; i < v1.size(); i++) {
            res += (float)v1.get(i) * (float)v2.get(i);
        }
        return res;
    }
    private float calculateNumerator(float a, float b, boolean isImplement) {
//        System.out.println("-------------------------");
//        System.out.println("a " + a);
//        System.out.println("b " + b);
//        System.out.println("isImplement " + isImplement);
//        System.out.println("-------------------------");
        if (!isImplement)
            return (1-b)*a;
        else
            return a*b;
    }
    private Vector findElements(Vector vector, int param) {
        Vector res = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            ThreatGroup group = (ThreatGroup)vector.get(i);
            if (group.getThreatId() == param)
                res.add(group.getFactor());
        }
        return res;
    }
    private float findFactor(Vector vector, int param1, int param2) {
        float res = -1;
        for (int i = 0; i < vector.size(); i++) {
            ThreatGroup group = (ThreatGroup)vector.get(i);
            if (group.getThreatId() == param1 && group.getLevelId() == param2)
               res = group.getFactor();
               //System.out.println("find Factor: " + res);
        }
        return res;
    }
    private void findConfidence() {
        float max = 0;
        findLevel = -1;
        for (int i = resultStatistic.size()-3; i < resultStatistic.size(); i++) {
                ThreatGroup element = new ThreatGroup();
                element = resultStatistic.get(i);
                float commonFactor = commonLevel.get(element.getLevelId());
                float diff = element.getFactor() - commonFactor;
                if (diff > 0 && diff > max) {
                    max = diff;
                    findLevel = element.getLevelId();
                }
//               System.out.println(diff);
//               System.out.println(levelId);
        }
    }
    public void createPentestList() {
        for (int i = 0; i < threatId.size(); i++) {
          pentestResult.put((Integer)threatId.get(i), false);  
        }
    }
    public void setPentestResult(Integer num, Boolean isImplement) {
        pentestResult.put(num, isImplement);
//        for(Map.Entry<Integer, Boolean> entry : pentestResult.entrySet()) {
//            System.out.println("pentestResult key: " + entry.getKey());
//            System.out.println("pentestResult value: " + entry.getValue());
//        }
    }
    public void setSelectedLevel(int num) {
        selectedLevel = num;
    }
    public Vector loadThreatPercent(JTable table) {
        Vector res = new Vector();
        for (int i = 0; i < threatGroup.size(); i++) {
           ThreatGroup group = new ThreatGroup();
           group = (ThreatGroup)threatGroup.get(i);
           if (group.getLevelId() == selectedLevel) {
               res.add(group.getPercent());
           }
        }
        return res;
    }
    public void setGroupId(Vector groupId) {
       this.levelId = groupId;
    }
    public void setThreatId(Vector threatId) {
        this.threatId = threatId;  
    }
    public int getThreatCount() {
        return resultStatistic.size();
    }
    public int getLevelCount() {
        return commonLevel.size();
    }
    public int getFindLevel() {
        return findLevel;
    }
}
