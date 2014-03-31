package view;

import controller.MainController;
import java.util.Vector;

public class SecurityLevePanel extends javax.swing.JPanel {

    private MainController control = null;
    private TablesView tableLevelSecurity = null;
   
    public SecurityLevePanel() {
        initComponents();
        control = MainController.getInstance();
        tableLevelSecurity = new TablesView(LevelSecurityTable);
        showTables();
        loadLevelsComboBox();
        tableLevelSecurity.addModel();
    }

    private Vector columnsName(MainController.TextQuery _table) {
        Vector columnNames = new Vector();     
        switch (_table) {
            case SECURITYLEVEL:
                 columnNames.addElement("Num");
                 columnNames.addElement("Level");
                 columnNames.addElement("Description");
                 columnNames.addElement("Percentage");
                break;
            case THREATS:
                break;
            default:
                break;
        }
         return columnNames;
    }
    
    private void showTables() {
        //First table - security level in percentage
         tableLevelSecurity.data = control.findRecord(control.textQuery.SECURITYLEVEL);
         tableLevelSecurity.columns = columnsName(MainController.TextQuery.SECURITYLEVEL);
         tableLevelSecurity.fillTable();
    }
    
     private void loadLevelsComboBox() {
        Vector res = control.findRecord(control.textQuery.SECURITYLEVEL);
        for (int i = 0; i < res.size(); i++ ) {
            int index = (Integer)((Vector)res.get(i)).get(0);
            String value = ((Vector)res.get(i)).get(1).toString();
                                     
            LevelComboBox.addItem(new Item(index, value));
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LevelSecurityScrollPane = new javax.swing.JScrollPane();
        LevelSecurityTable = new javax.swing.JTable();
        LevelComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        LevelSecurityTable.setModel(new javax.swing.table.DefaultTableModel(
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
        LevelSecurityScrollPane.setViewportView(LevelSecurityTable);

        LevelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevelComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Группа");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LevelSecurityScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(LevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(399, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(LevelSecurityScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LevelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LevelComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox LevelComboBox;
    private javax.swing.JScrollPane LevelSecurityScrollPane;
    private javax.swing.JTable LevelSecurityTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
