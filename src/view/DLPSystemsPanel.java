package view;

import controller.MainController;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DLPSystemsPanel extends javax.swing.JPanel {

    private MainController control = null;
    
    public DLPSystemsPanel() {
        
        control = MainController.getInstance();
        initComponents();
        fillTable();
        //Action listener for button "Save"
        ActionListener saveActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //void saveDLPSystem();
                System.out.println("Save button click");
            }
        };
        SaveButton.addActionListener(saveActionListener);
    }
    private void fillTable() {
        //Columns name 
        Vector columnNames = new Vector();
         columnNames.addElement("Title");
         columnNames.addElement("Information");
         columnNames.addElement("Country");
         columnNames.addElement("Offical site");
          
         //Rows data
         Vector data = control.showDLPSystems();
        
        //Fill table 
        //DLPTable.setPreferredScrollableViewportSize(new Dimension(800, 200));
       // DLPTable.setFillsViewportHeight(true);
        DLPTable.setModel(new DefaultTableModel(data, columnNames));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DLPScrollPane = new javax.swing.JScrollPane();
        DLPTable = new javax.swing.JTable();
        SaveButton = new javax.swing.JButton();

        DLPTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        DLPScrollPane.setViewportView(DLPTable);

        SaveButton.setText("Save");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(183, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(DLPScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(SaveButton)
                        .addGap(210, 210, 210))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(DLPScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SaveButton)
                .addGap(0, 43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane DLPScrollPane;
    private javax.swing.JTable DLPTable;
    private javax.swing.JButton SaveButton;
    // End of variables declaration//GEN-END:variables
}
