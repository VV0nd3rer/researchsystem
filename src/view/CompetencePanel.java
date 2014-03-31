package view;

import controller.MainController;
import java.util.Vector;
import javax.swing.*;

public class CompetencePanel extends JPanel {

    private MainController control = null;
    private TablesView tableView = null;
    public CompetencePanel() {
        control = MainController.getInstance();
        initComponents();
        tableView = new TablesView(CompetenceTable);
        showTable();
    }

   private Vector columnsName() {
        Vector columnNames = new Vector();     
         columnNames.addElement("Num");
         columnNames.addElement("Expert");
         columnNames.addElement("Common mark");
         columnNames.addElement("Competence");
         return columnNames;
    }
     private void showTable() {
         tableView.data = control.findRecord(control.textQuery.COMPETENCE);
         tableView.columns = columnsName();
         tableView.fillTable();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CompetenceScrollPane = new javax.swing.JScrollPane();
        CompetenceTable = new javax.swing.JTable();

        CompetenceTable.setModel(new javax.swing.table.DefaultTableModel(
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
        CompetenceScrollPane.setViewportView(CompetenceTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(CompetenceScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CompetenceScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(371, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane CompetenceScrollPane;
    private javax.swing.JTable CompetenceTable;
    // End of variables declaration//GEN-END:variables
}
