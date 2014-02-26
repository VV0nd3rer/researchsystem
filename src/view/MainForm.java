
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
    
    private JTextField loginField = new JTextField();
    private JTextField passField = new JTextField();
  
//    private JLabel hiLabel = new JLabel("Hi")
    
    private JButton loginButton = new  JButton("Ok");
        
    private MainController control = null;
        
       
    public MainForm() {
       // conrol = 
        //Auth panel
        authPanel.setLayout(null);
        loginField.setBounds(15,15, 150, 25);
        passField.setBounds(15, 40, 150, 25);
        loginButton.setBounds(40, 80, 60, 40);
        authPanel.add(loginField);
        authPanel.add(passField);
        authPanel.add(loginButton);
        
        //User panel
//        userPanel.setLayout(null);
//        hiLabel.setBounds(50, 80, 150, 25);
//        userPanel.add(hiLabel);
        
        control = MainController.getInstance();
        
        guiFrame.add(authPanel);
        guiFrame.setSize(300,300);
        guiFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        guiFrame.setVisible(true);
   
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                 // if (control.CheckUser(loginField, passField)) {
                    //control.SayHello(hiLabel);
                    guiFrame.remove(authPanel);
                    //guiFrame.add(userPanel);
                    createAnalystTab(guiFrame.getContentPane());
                    guiFrame.revalidate();
                 // }
            }
        });
        
 }
    
    private void createAnalystTab(Container pane) {
         final JTabbedPane analystPane = new JTabbedPane();
        
        //Security.
        JPanel security = new JPanel() {
            //Make the panel wider than it really needs, so
            //the window's wide enough for the tabs to stay
            //in one row.
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
        security.add(new JLabel("It's coming soon..."));
      
        // JScrollPane DLPTable = fillTable();
        //JPanel DLPSystems = new JPanel();
//        DLPSystems.add(DLPTable);
//        DLPSystems.add(new JButton("Save"));
//        DLPSystems.add(new JButton("+"));
//        DLPSystems.add(new JButton("-"));
        
        JPanel DLPSystems = new DLPSystemsPanel();
        
        
        analystPane.addTab(SECURITYPANEL, security);
        analystPane.addTab(DLPPANEL, DLPSystems);
 
        pane.add(analystPane, BorderLayout.CENTER);
    }
    
//    private JScrollPane fillTable() {
//         Vector columnNames = new Vector();
//         columnNames.addElement("Title");
//         columnNames.addElement("Information");
//         columnNames.addElement("Country");
//         columnNames.addElement("Offical site");
//          
//         Vector data = control.showDLPSystems();
//           
//        JTable table = new JTable(data, columnNames);
//        table.setPreferredScrollableViewportSize(new Dimension(800, 200));
//        table.setFillsViewportHeight(true);
//        JScrollPane scrollPane = new JScrollPane(table);
//        
//        return scrollPane;
//    }
    
//    public void showEntry() {
//        
//       if (control.CheckUser(loginField, passField))
//                control.SayHello(hiLabel);
//            guiFrame.remove(authPanel);
//            guiFrame.add(userPanel);
//            guiFrame.revalidate();
//    }
}

