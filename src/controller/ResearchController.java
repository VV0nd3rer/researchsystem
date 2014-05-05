package controller;

import java.util.Map;
import model.Criterias;
import java.util.Vector;


public class ResearchController {
    private Criterias criteria = new Criterias();
    public void findCompetenceCriterias() {
        int id = 1;
        criteria.selectCompetenceCriterias(id);
    }
    public void printCompetentCriteria() {
        //criteria.printCompetenceCriterias();
        for(Map.Entry<Integer, Float> entry : criteria.competenceCriteriasMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
   
}
    