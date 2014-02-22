package controller;

import javax.swing.*;
import model.User;

public class MainController {
    private User analyst;
    
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
    
    public void SayHello(JLabel helloLabel) {
         helloLabel.setText("Hello, " + analyst.getUserName());
    }
}
