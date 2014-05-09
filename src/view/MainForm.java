
package view;

import javax.swing.*;
import java.awt.*;
import controller.MainController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainForm  {
    final static int extraWindowWidth = 100;
    private MainController control = null;
    //The main frame for all panels
    private JFrame guiFrame = new JFrame();
    //Panels for authentification, 
    private JPanel authPanel = new JPanel();
    //private JPanel userPanel = new JPanel();
    private JPanel auditPanel = null;
    private JPanel researchPanel = null;
//    private JPanel securityLevelPanel = null;
    private JPanel dataPanel = null;
    private JPanel competencePanel = null;
//    private JTabbedPane mainPanels = new JTabbedPane();
    
    private JTextField loginField = new JTextField();
    private JTextField passField = new JTextField();
  
    private JButton loginButton = new  JButton("Ok");
    private JButton auditButton = new JButton("->");
    private JButton optimizeButton = new JButton("Optimize");
           
    public MainForm() {
        control = MainController.getInstance();
        //Auth panel
        authPanel.setLayout(null);
        loginField.setBounds(15,15, 150, 25);
        passField.setBounds(15, 40, 150, 25);
        loginButton.setBounds(40, 80, 60, 40);
        authPanel.add(loginField);
        authPanel.add(passField);
        authPanel.add(loginButton);
        
        guiFrame.add(authPanel);
        setFrameSize(300, 300);
        guiFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        guiFrame.setVisible(true);
   
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                 // if (control.CheckUser(loginField, passField)) 
                    guiFrame.remove(authPanel);
                   // createAnalystTab(guiFrame.getContentPane());
                   createPanels();
                   //guiFrame.add(auditPanel);
                   setFrameSize(700, 700);
                   guiFrame.revalidate();
                 // }
            }
        });
        
        auditButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    control.hidePanels();
                    setFrameSize(1200, 800);
                    createSucuretyLevelPanel();
                    guiFrame.add(control.getSecurityLevelPanel());
                    guiFrame.revalidate();
            }
        });
        
        optimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.hidePanels();
                setFrameSize(1200, 1000);
                createOptimizePanel();
                guiFrame.add(control.getOptimizePanel());
                guiFrame.revalidate();
            }
        });
 }
    
    private void createPanels() {
        createAuditPanel();
        createResearchPanel();
        createDataPanel();
        createCompetencePanel();
        control.getTabPanels().addTab("Audits", auditPanel);
        control.getTabPanels().addTab("Researches", researchPanel);
        control.getTabPanels().addTab("Data", dataPanel);
        control.getTabPanels().addTab("Competence", competencePanel);
        guiFrame.getContentPane().add(control.getTabPanels(), BorderLayout.CENTER);
    }
   
    private void setFrameSize(int w, int h) {
        guiFrame.setSize(w, h);
    }
    //Пример, как делать меню - табуляцию
    
//    private void createAnalystTab(Container pane) {
//         final JTabbedPane analystPane = new JTabbedPane();
//        
//        //Security.
//        JPanel security = new SecurityLevePanell() {
//            //Make the panel wider than it really needs, so
//            //the window's wide enough for the tabs to stay
//            //in one row.
//            public Dimension getPreferredSize() {
//                Dimension size = super.getPreferredSize();
//                size.width += extraWindowWidth;
//                return size;
//            }
//        };
//      
//        //security.add(new JLabel("It's coming soon..."));
//        
//        //DLP-systems panel CRUD
//        JPanel DLPSystems = new DLPSystemsPanel();
//        
//        
//        analystPane.addTab(SECURITYPANEL, security);
//        analystPane.addTab(DLPPANEL, DLPSystems);
// 
//        pane.add(analystPane, BorderLayout.CENTER);
//    }
    
    private void createAuditPanel() {
        auditPanel = new AuditPanel();
        auditButton.setBounds(220, 380, 50, 30);
        auditPanel.add(auditButton);
    }
    private void createResearchPanel() {
        researchPanel = new ResearchPanel();
        optimizeButton.setBounds(220, 380, 100, 30);
        researchPanel.add(optimizeButton);
    }
    private void createDataPanel() {
        dataPanel = new DataPanel();
    }
    private void createCompetencePanel() {
        competencePanel = new CompetencePanel();
    }
    private void createSucuretyLevelPanel() {
        //Контроллер - занести выбранное предприятие и начать определение ур-ня безопасности
        //ИС предприятия используя байесовский метод
        control.setSecurityLevelPanel(new SecurityLevePanel(control.getEnterpriseName()));
    }
    private void createOptimizePanel() {
        control.setOptimizePanel(new OptimizePanel());
    }
}

