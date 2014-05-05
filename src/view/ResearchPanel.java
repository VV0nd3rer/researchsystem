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
        ResearchTable = new JTable() {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    return String.class;
                    case 1:
                    return String.class;
                    case 2:
                    return String.class;
                    case 3:
                    return Boolean.class;
                    default:
                    return getValueAt(0, column).getClass();
                }
            }
        };
        OkButton = new javax.swing.JButton();

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

        OkButton.setText("Ok");
        OkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(OkButton)
                    .addComponent(ResearchScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(ResearchScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OkButton)
                .addContainerGap(186, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void OkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkButtonActionPerformed
        control.findCompetenceCriterias();
    }//GEN-LAST:event_OkButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OkButton;
    private javax.swing.JScrollPane ResearchScrollPane;
    private javax.swing.JTable ResearchTable;
    // End of variables declaration//GEN-END:variables
}
