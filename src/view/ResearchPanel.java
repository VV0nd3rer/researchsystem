package view;

import controller.MainController;
import java.util.Vector;
import javax.swing.*;
public class ResearchPanel extends JPanel {
    private MainController control = null;
    private TablesView tableView = null;
    public ResearchPanel() {
        control = MainController.getInstance();
        initComponents();
        tableView = new TablesView(ResearchTable);
        showTable();
    }
    private Vector columnsName() {
        Vector columnNames = new Vector();     
         columnNames.addElement("Num");
         columnNames.addElement("Enterprise");
         columnNames.addElement("Best DLP");
         columnNames.addElement("Research done");
         return columnNames;
    }
     private void showTable() {
         tableView.data = control.findRecord(control.textQuery.RESEARCH);
         tableView.columns = columnsName();
         tableView.fillTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ResearchScrollPane = new javax.swing.JScrollPane();
        ResearchTable = new javax.swing.JTable();

        ResearchTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ResearchScrollPane.setViewportView(ResearchTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(ResearchScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(ResearchScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ResearchScrollPane;
    private javax.swing.JTable ResearchTable;
    // End of variables declaration//GEN-END:variables
}
