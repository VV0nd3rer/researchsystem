package controller;

import javax.swing.*;
import java.util.Vector;
import model.User;

public class MainController {
    private User analyst;
    
    private static MainController instance = null;
    
    public static MainController getInstance() {
            if(instance == null) {
                instance = new MainController();
            }
            return instance;
     }
    
    public MainController() {
        analyst = new User();  
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
    
    public Vector/*<Vector<String>>*/ showDLPSystems() {
         analyst.findDLPSystems();
         return analyst.getDLPSystems();
    }
    
    public void editDLPSystems(Vector _num, Vector _data) {
        analyst.editDLPSystems(_num, _data);
    }
    
    public void createDLPSystem(Vector _data) {
        analyst.createDLPSystem(_data);
    }
    
    public void deleteDLPSystem(int _num) {
        analyst.deleteDLPSystem(_num);
    }
    
    public void SayHello(JLabel helloLabel) {
         helloLabel.setText("Hello, " + analyst.getUserName());
    }
}
