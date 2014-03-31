package view;
import controller.MainController;
import java.util.Vector;
import javax.swing.*;

public class DataPanel extends JPanel {
    private MainController control = null;
    private TablesView tableView = null;
    public DataPanel() {
        control = MainController.getInstance();
        initComponents();
        tableView = new TablesView(ParamTable);
        showTable();
    }

     private Vector columnsName() {
        Vector columnNames = new Vector();   
         columnNames.addElement("Parameter");
         return columnNames;
    }
     private void showTable() {
         tableView.data = control.findRecord(control.textQuery.DATA);
         tableView.columns = columnsName();
         tableView.fillTable();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ParamScrollPane = new javax.swing.JScrollPane();
        ParamTable = new javax.swing.JTable();

        ParamTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ParamScrollPane.setViewportView(ParamTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(ParamScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ParamScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ParamScrollPane;
    private javax.swing.JTable ParamTable;
    // End of variables declaration//GEN-END:variables
}
