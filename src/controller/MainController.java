package controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import model.*;
  
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
    private User user;

    
    private Enterprises enterprises;
    private SecurityLevel securityLevel;
    private InputParameters inputParam;
    
    private static MainController instance = null;
    SecurityLevelController secLevel = new SecurityLevelController();
    //How it do better
    private JTabbedPane mainPanels = new JTabbedPane();
    private JPanel securityLevelPanel = null;
    
    public enum TextQuery {
        //audit
        AUDITCLIENT, 
        // Bayesian approach
        SECURITYLEVEL, THREATS, PENTEST,
        ENTERPRISES,
        RESEARCH, DATA, COMPETENCE
    }
    
    public TextQuery textQuery;
    
    public static MainController getInstance() {
            if(instance == null) {
                instance = new MainController();
            }
            return instance;
     }
    
    public MainController() {
        user = new User();  
        enterprises = new Enterprises();
        securityLevel = new SecurityLevel();
        inputParam = new InputParameters();   
    }

    public void createThreatList() {
        secLevel.createThreatList();
    }
    public void setMainTabPanels(JTabbedPane tabPane) {
        
    }
    public void setSecurityLevelPanel(JPanel panel) {
        securityLevelPanel = panel;
    }
    public JTabbedPane getTabPanels() {
        return mainPanels;
    }
    public JPanel getSecurityLevelPanel() {
        return securityLevelPanel;
    }
   public void addCommonPercent(Integer num, Float percent) {
       secLevel.setCommonPercent(num, percent);
   }
   public void addThreatPercent(Integer num, Float percent) {
       secLevel.setThreatPercent(num, percent);
   }
   public void setSelectedLevel(int num) {
       secLevel.setSelectedLevel(num);
   }
   public Vector loadThreatPercent() {
       return secLevel.loadThreatPercent();
   }
//   public void addSecurityLevelId(int num) {
//       secLevel.setGroupId(num);
//   }
    public int getComboBoxId(ActionEvent evt, JComboBox comboBox) {
         if (evt.getSource().equals(comboBox)) {
             comboBox = (JComboBox) evt.getSource();
             Item item = (Item) comboBox.getSelectedItem();
             if(item!=null){
                  System.out.println("ComboBox id:"+item.getId());
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
                  System.out.println("ComboBox value:"+item.getDescription());
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
    public boolean CheckUser(JTextField textLogin, JTextField textPass) {
        user.setLogin(textLogin.getText());
        user.setPass(textPass.getText());
        user.findUser();
        if (user.getUserId() != 0) {
            System.out.println("Hello, " + user.getUserName());
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
                res = inputParam.getInputParameters();
                break;
            case COMPETENCE:
                res = user.getExperts();
                break;
            case AUDITCLIENT:
                res = enterprises.getAudits();
                break;
            case SECURITYLEVEL:
                res = securityLevel.getLevelRecords();
                secLevel.setGroupId(getColumn(res));
                break;
            case THREATS:
            case PENTEST:
                res = securityLevel.getThreatRecords();
                secLevel.setThreatId(getColumn(res));
                break;
            case ENTERPRISES:
                res = enterprises.getEnterprises();
                break;
            case RESEARCH:
                res = enterprises.getResearches();
                break;
            default:
                break; 
        }
         return res;
    }
    public void determineSecurityLevel() {
        
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
//    public void createRecord(Vector _data, TextQuery _table) {
//         switch (_table) {
//            case DLPSYSTEM:
//                dlpSystems.createDLPSystems(_data);
//                break;
//            case AUDITCLIENT:
//                enterprises.createEnterprise(_data);
//                break;
//            default:
//                break; 
//        }
//    }
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

