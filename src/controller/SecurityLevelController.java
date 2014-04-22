package controller;
import java.util.Vector;
import java.util.*;

class ThreatGroup {
    private int levelId;
    private int threatId;
    private float percent;

    public ThreatGroup(int threatId, int levelId) {
        this.threatId = threatId;
        this.levelId = levelId;
        this.percent = 0.0f;
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
     }
     public float getPercent() {
         return percent;
     }
}

public class SecurityLevelController {

//    CommonGroup commonGroup = new CommonGroup();
//    public void setCommonPercent(int num, float percent) {
//        commonGroup.setPercent(num, percent);
//    }
    private Vector levelId = new Vector();
    private Vector percent = new Vector();
    private Vector threatId = new Vector();
    private Vector threatGroup = new Vector();
    private int selectedLevel;
    private Map<Integer, Float> commonLevel = new HashMap<Integer, Float>();
    private Map<Integer, Object> threatLevel = new HashMap<Integer, Object>();
    
    //General statistics about the level of information system security
    public void setCommonPercent(Integer num, Float percent) {
        for (int i = 0; i < commonLevel.size(); i++) {
            System.out.println("Element of commonLevel: " + commonLevel.get(i).toString());
        }
        commonLevel.put(num, percent);
    }
    public void createThreatList() {
        for (int i = 0; i < levelId.size(); i++) {
            for (int j = 0; j < threatId.size(); j++) {
               threatGroup.add(new ThreatGroup((int)threatId.get(j), (int)levelId.get(i)));
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
                   System.out.println("----------setThreatPercent---------------");
                   System.out.println("levelId: " + group.getLevelId());
                   System.out.println("threatId: " + group.getThreatId());
                   System.out.println("percent: " + percent);
               }
           }
        }
    }
    public void setSelectedLevel(int num) {
        selectedLevel = num;
    }
    public Vector loadThreatPercent() {
        Vector res = new Vector();
        for (int i = 0; i < threatGroup.size(); i++) {
           ThreatGroup group = new ThreatGroup();
           group = (ThreatGroup)threatGroup.get(i);
           if (group.getLevelId() == selectedLevel) {
               res.add(group.getPercent());
                   System.out.println("----------setThreatPercent---------------");
                   System.out.println("levelId: " + group.getLevelId());
                   System.out.println("threatId: " + group.getThreatId());
                   System.out.println("percent: " + group.getPercent());
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
    public void setPercent(Vector percent) {
        this.percent = percent;
    }
}
