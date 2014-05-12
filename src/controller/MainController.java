package controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import model.*;
import view.OptimizePanel;
  
//For work with comboBox
//http://stackoverflow.com/questions/
 class Item {
        private int id;
        private String description;
        public Item(int id, String description) {
            this.id = id;
            this.description = description;
        }
        public int getId() {
            return id;
        }
        public String getDescription() {
            return description;
        }
        public String toString() {
            return description;
        }
}

public class MainController {
    private User userModel;
    private Enterprises enterprisesModel;
    private SecurityLevel securityLevelModel;
    private InputParameters inputParamModel;
    private DlpSystems dlpModel;
    
    private static MainController instance = null;
    SecurityLevelController securityLevelControl = new SecurityLevelController();
    ResearchController researchControl = new ResearchController();
    //How it do better
    private JTabbedPane mainPanels = new JTabbedPane();
    private JPanel securityLevelPanel = null;
    private JPanel optimizePanel = null;
    
    public enum TextQuery {
        //audit
        AUDITCLIENT, 
        // Bayesian approach
        SECURITYLEVEL, THREATS, PENTEST,
        ENTERPRISES, DLPSYSTEMS,
        RESEARCH, DATA, COMPETENCE,
        CRITERIAESTIMATE, DLPESTIMATE
    }
    
    public TextQuery textQuery;
    
    public static MainController getInstance() {
            if(instance == null) {
                instance = new MainController();
            }
            return instance;
     }
    
    public MainController() {
        userModel = new User();  
        enterprisesModel = new Enterprises();
        securityLevelModel = new SecurityLevel();
        inputParamModel = new InputParameters();
        dlpModel = new DlpSystems();
    }
    public void createResearch() {
        researchControl.createResearch();
        researchControl.printResults();
        researchControl.drawGraph();
    }
    public void selectEnterpriseName(String name) {
        securityLevelModel.setSelectEnterpriseName(name);
    }
    public String getEnterpriseName() {
        return securityLevelModel.getSelectEnterpriseName();
    }
    public void selectEnterpriseId(int id) {
        securityLevelModel.setSelectEnterpriseId(id);
    }
    public int getEnterpriseId() {
        return securityLevelModel.getSelectEnterpriseId();
    }
    public void createThreatList() {
        securityLevelControl.createThreatList();
    }
    public void createPentestList() {
        securityLevelControl.createPentestList();
    }
    public void determineSecurityLevel() {
        //securityLevelControl.createResultList();
        securityLevelControl.setResultStatistic();
    }
    public Vector showResults() {
        return securityLevelControl.getResultStatistic();
    }
    public int getThreatCount() {
        return securityLevelControl.getThreatCount();
    }
    public int getLevelCount() {
        return securityLevelControl.getLevelCount();
    }
    public int getResultLevel() {
        return securityLevelControl.getFindLevel();
    }
    public void addCommonPercent(Integer num, Float percent) {
       securityLevelControl.setCommonPercent(num, percent);
    }
    public void addThreatPercent(Integer num, Float percent) {
       securityLevelControl.setThreatPercent(num, percent);
    }
    public void addPentestResult(Integer num, Boolean isImplement) {
       securityLevelControl.setPentestResult(num, isImplement);
    }
    public void setSelectedLevel(int num) {
       securityLevelControl.setSelectedLevel(num);
    }
    public Vector loadThreatPercent(JTable table) {
       return securityLevelControl.loadThreatPercent(table);
    }
    public void setSecurityLevelPanel(JPanel panel) {
       securityLevelPanel = panel;
    }
    public void setOptimizePanel(JPanel panel) {
        optimizePanel = panel;
    }
    public JTabbedPane getTabPanels() {
       return mainPanels;
    }
    public JPanel getSecurityLevelPanel() {
       return securityLevelPanel;
    }
    public JPanel getOptimizePanel() {
        return optimizePanel;
    }
    public int getComboBoxId(ActionEvent evt, JComboBox comboBox) {
         if (evt.getSource().equals(comboBox)) {
             comboBox = (JComboBox) evt.getSource();
             Item item = (Item) comboBox.getSelectedItem();
             if(item!=null){
                 // System.out.println("ComboBox id:"+item.getId());
                  return item.getId();
                }
        }
         return -1;
    }
    public String getComboBoxValue(ActionEvent evt, JComboBox comboBox) {
        if (evt.getSource().equals(comboBox)) {
             comboBox = (JComboBox) evt.getSource();
             Item item = (Item) comboBox.getSelectedItem();
             if(item!=null){
                 // System.out.println("ComboBox value:"+item.getDescription());
                  return item.getDescription();
                }
        }
         return null;
    }
    public void loadComboBox(TextQuery _table, JComboBox comboBox) {
        Vector res = findRecord(_table);
        for (int i = 0; i < res.size(); i++ ) {
            int index = (Integer)((Vector)res.get(i)).get(0);
            String value = ((Vector)res.get(i)).get(1).toString();
                                     
            comboBox.addItem(new Item(index, value));
        }
    }
   public void hidePanels() {
        mainPanels.setVisible(false);
    }
   public void showPanels() {
       mainPanels.setVisible(true);
   }
   public void hideSecurityLevelPanel() {
       securityLevelPanel.setVisible(false);
   }
   public void showSecurityLevelPanel() {
       securityLevelPanel.setVisible(true);
   }
   public void hideOptimizePanel() {
       optimizePanel.setVisible(false);
   }
    public boolean CheckUser(JTextField textLogin, JTextField textPass) {
        userModel.setLogin(textLogin.getText());
        userModel.setPass(textPass.getText());
        userModel.findUser();
        if (userModel.getUserId() != 0) {
            System.out.println("Hello, " + userModel.getUserName());
            return true;
        }
        else 
            return false;
    }
    private Vector getColumn(Vector rows) {
        Vector columns = new Vector();
        for (int i = 0; i < rows.size(); i++) {
                    columns.add((int)((Vector)rows.get(i)).get(0));
        }
        return columns;
    }
    public Vector findRecord(TextQuery _table) {
        Vector res = new Vector();
        switch (_table) {
            case DATA:
                res = inputParamModel.getInputParameters();
                break;
            case COMPETENCE:
                res = userModel.getExperts();
                break;
            case AUDITCLIENT:
                res = enterprisesModel.getAudits();
                break;
            case SECURITYLEVEL:
                res = securityLevelModel.getLevelRecords();
                securityLevelControl.setGroupId(getColumn(res));
                break;
            case THREATS:
            case PENTEST:
                res = securityLevelModel.getThreatRecords();
                securityLevelControl.setThreatId(getColumn(res));
                break;
            case ENTERPRISES:
                res = enterprisesModel.getEnterprises();
                break;
            case RESEARCH:
                res = enterprisesModel.getResearches();
                break;
            default:
                break; 
        }
         return res;
    }
    public Vector getResearchDlp(int researchId) {
        return dlpModel.getResearchDlp(researchId);
    }
    public Vector getTableHeader(Vector data) {
        Vector header = new Vector();
        for (int i = 0; i < data.size(); i++) {
            header.add(((Vector)data.get(i)).get(1));
        }
        return header;
    }
    public Vector determineCriteriasEstimates() {
      return researchControl.determineCriteriasEstimates();
    }
    public void determineDlpEstimates() {
         researchControl.determineDlpEstimates();
    }
    public Vector getDlpEstimates(int dlpId) {
        return researchControl.getDlpEstimates(dlpId);
    }
//    public void updateRecord(Vector _num, Vector _data, TextQuery _table) {
//       switch (_table) {
//            case DLPSYSTEM:
//                dlpSystems.updateDLPSystems(_num, _data);
//                break;
//            case AUDITCLIENT:
//                enterprises.updateEnterprise(_num, _data);
//                break;
//            case SECURITYLEVEL:
//                securityLevel.updateRecords(_num, _data, null);
//            default:
//                break; 
//        }
//    }
//    
    public void createRecord(Vector _data, TextQuery _table) {
         switch (_table) {
            case AUDITCLIENT:
                enterprisesModel.createAudit(_data);
                break;
            default:
                break; 
        }
    }
//    
//    public void deleteRecord(int _num, TextQuery _table) {
//        switch (_table) {
//            case DLPSYSTEM:
//                dlpSystems.deleteDLPSystem(_num);
//                break;
//            case AUDITCLIENT:
//                enterprises.deleteEnterprise(_num);
//                break;
//            default:
//                break; 
//        }
//    }

}

