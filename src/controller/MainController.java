package controller;

import javax.swing.*;
import java.util.Vector;
import model.*;

  

public class MainController {
    private User user;

    private Enterprises enterprises;
    private SecurityLevel securityLevel;
    private InputParameters inputParam;
    
    private static MainController instance = null;
    
    public enum TextQuery {
         AUDITCLIENT, SECURITYLEVEL, THREATS, ENTERPRISES,
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
                break;
            case THREATS:
                res = securityLevel.getThreatRecords();
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

