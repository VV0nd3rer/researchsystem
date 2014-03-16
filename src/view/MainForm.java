
package view;

import javax.swing.*;
import java.awt.*;
import controller.MainController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainForm  {
    final static String SECURITYPANEL = "Security";
    final static String DLPPANEL = "DLP-systems";
    final static int extraWindowWidth = 100;
    //The main frame for all panels
    private JFrame guiFrame = new JFrame();
    //Panels for authentification, 
    private JPanel authPanel = new JPanel();
    private JPanel userPanel = new JPanel();
    private JPanel auditPanel = null;
    
    private JTextField loginField = new JTextField();
    private JTextField passField = new JTextField();
  
    private JButton loginButton = new  JButton("Ok");
    private JButton auditButton = new JButton("->");
    
    private MainController control = null;
       
    public MainForm() {
        //Auth panel
        authPanel.setLayout(null);
        loginField.setBounds(15,15, 150, 25);
        passField.setBounds(15, 40, 150, 25);
        loginButton.setBounds(40, 80, 60, 40);
        authPanel.add(loginField);
        authPanel.add(passField);
        authPanel.add(loginButton);
        
        control = MainController.getInstance();
        
        guiFrame.add(authPanel);
        guiFrame.setSize(300,300);
        guiFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        guiFrame.setVisible(true);
   
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                 // if (control.CheckUser(loginField, passField)) 
                    guiFrame.remove(authPanel);
                   // createAnalystTab(guiFrame.getContentPane());
                   createAuditPanel();
                   guiFrame.add(auditPanel);
                    guiFrame.revalidate();
                 // }
            }
        });
        
        auditButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    guiFrame.remove(auditPanel);
                    createAnalystTab(guiFrame.getContentPane());        
                    guiFrame.revalidate();
            }
        });
        
 }
    
    private void createAnalystTab(Container pane) {
         final JTabbedPane analystPane = new JTabbedPane();
        
        //Security.
        JPanel security = new SecurityLevePanell() {
            //Make the panel wider than it really needs, so
            //the window's wide enough for the tabs to stay
            //in one row.
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
      
        //security.add(new JLabel("It's coming soon..."));
        
        //DLP-systems panel CRUD
        JPanel DLPSystems = new DLPSystemsPanel();
        
        
        analystPane.addTab(SECURITYPANEL, security);
        analystPane.addTab(DLPPANEL, DLPSystems);
 
        pane.add(analystPane, BorderLayout.CENTER);
    }
    
    private void createAuditPanel() {
        auditPanel = new AuditPanel();
        auditButton.setBounds(300, 280, 60, 40);
        auditPanel.add(auditButton);
    }
    
}

