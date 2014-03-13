package controller;

import javax.swing.*;
import java.util.Vector;
import model.*;

  

public class MainController {
    private User analyst;
    private DLPSystems dlpSystems;
    private Enterprises enterprises;

    private static MainController instance = null;
    
    public enum TextQuery {
        DLPSYSTEM, AUDITCLIENT
    }
    
    public TextQuery textQuery;
    
    public static MainController getInstance() {
            if(instance == null) {
                instance = new MainController();
            }
            return instance;
     }
    
    public MainController() {
        analyst = new User();  
        dlpSystems = new DLPSystems();
        enterprises = new Enterprises();
    }
    
    public boolean CheckUser(JTextField textLogin, JTextField textPass) {
        analyst.setLogin(textLogin.getText());
        analyst.setPass(textPass.getText());
        analyst.findUser();
        if (analyst.getUserId() != 0) {
            System.out.println("Hello, " + analyst.getUserName());
            return true;
        }
        else 
            return false;
    }
    
    public Vector findRecord(TextQuery _table) {
        Vector res = new Vector();
        switch (_table) {
            case DLPSYSTEM:
                res = dlpSystems.getRecords();
                break;
            case AUDITCLIENT:
                res = enterprises.getRecords();
                break;
            default:
                break; 
        }
         return res;
    }
    
    public void updateRecord(Vector _num, Vector _data, TextQuery _table) {
       switch (_table) {
            case DLPSYSTEM:
                dlpSystems.updateDLPSystems(_num, _data);
                break;
            case AUDITCLIENT:
                enterprises.updateEnterprise(_num, _data);
                break;
            default:
                break; 
        }
    }
    
    public void createRecord(Vector _data, TextQuery _table) {
         switch (_table) {
            case DLPSYSTEM:
                dlpSystems.createDLPSystems(_data);
                break;
            case AUDITCLIENT:
                enterprises.createEnterprise(_data);
                break;
            default:
                break; 
        }
    }
    
    public void deleteRecord(int _num, TextQuery _table) {
        switch (_table) {
            case DLPSYSTEM:
                dlpSystems.deleteDLPSystem(_num);
                break;
            case AUDITCLIENT:
                enterprises.deleteEnterprise(_num);
                break;
            default:
                break; 
        }
    }
}
