package view;

import controller.MainController;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

public class DLPSystemsPanel extends JPanel {

    private MainController control = null;
    private TablesView tableView = null;
   
    public DLPSystemsPanel() {
        
        control = MainController.getInstance();
        initComponents();
        tableView = new TablesView(DLPTable);
        showTable();
        tableView.addIdModel();
    }
    
    private Vector columnsName() {
         Vector columnNames = new Vector();
         columnNames.addElement("Num");
         columnNames.addElement("Title");
         columnNames.addElement("Information");
         columnNames.addElement("Country");
         columnNames.addElement("Offical site");
         return columnNames;
    }
    
    private void showTable(){
         tableView.data = control.findRecord(control.textQuery.DLPSYSTEM);
         tableView.fillTable(columnsName());
   }
    
    private void deleteDLPSystem() {
        int delNum = (Integer)((Vector)tableView.data.get(DLPTable.getSelectedRow())).get(0);
        control.deleteRecord(delNum, control.textQuery.DLPSYSTEM);
        tableView.deleteRow();
    }
    
    private void addDLPSystem() {
        tableView.addRow();
    }

    private void saveDLPSystem() {
       boolean isNew = false;
       for (int i = 0; i < tableView.changeNum.size(); i++) {
           if (tableView.changeNum.get(i) == "") {
                isNew = true;
           }        
       }
       if (isNew)
           control.createRecord(tableView.data, control.textQuery.DLPSYSTEM);
       else 
          control.updateRecord(tableView.changeNum, tableView.data, control.textQuery.DLPSYSTEM);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DLPScrollPane = new javax.swing.JScrollPane();
        DLPTable = new javax.swing.JTable();
        SaveButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        NewButton = new javax.swing.JButton();

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
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        NewButton.setText("New");
        NewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(DLPScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(SaveButton)
                        .addGap(27, 27, 27)
                        .addComponent(DeleteButton)
                        .addGap(28, 28, 28)
                        .addComponent(NewButton)
                        .addGap(147, 147, 147))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(DLPScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(DeleteButton)
                    .addComponent(NewButton))
                .addGap(0, 30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        deleteDLPSystem();
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void NewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewButtonActionPerformed
        addDLPSystem();
    }//GEN-LAST:event_NewButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
      saveDLPSystem();
    }//GEN-LAST:event_SaveButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane DLPScrollPane;
    private javax.swing.JTable DLPTable;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton NewButton;
    private javax.swing.JButton SaveButton;
    // End of variables declaration//GEN-END:variables
}
