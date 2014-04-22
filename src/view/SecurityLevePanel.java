package view;

import controller.MainController;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SecurityLevePanel extends javax.swing.JPanel {

    private MainController control = null;
    private TablesView tableLevelSecurity = null;
    private TablesView tableThreat = null;
    private TablesView tablePentest = null;
   
    public SecurityLevePanel() {
        initComponents();
        control = MainController.getInstance();
        tableLevelSecurity = new TablesView(LevelSecurityTable);
        tableThreat = new TablesView(ThreatTable);
        tablePentest = new TablesView(PentestTable);
        showTables();
        loadLevelsComboBox();
        tableLevelSecurity.addModelListener(control.textQuery.SECURITYLEVEL);
        tableLevelSecurity.addSelectionListener();
        tableThreat.addModelListener(control.textQuery.THREATS);
        tablePentest.addModelListener(control.textQuery.PENTEST);
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
                columnNames.addElement("Num");
                columnNames.addElement("Threat");
                columnNames.addElement("Percentage");
                break;
            case PENTEST:
                columnNames.addElement("Num");
                columnNames.addElement("Threat");
                columnNames.addElement("Is realized");
            default:
                break;
        }
         return columnNames;
    }
    
    private void showTables() {
        //First table - security level in percentage
        findData(tableLevelSecurity, control.textQuery.SECURITYLEVEL);
        //Second table - threat level
        findData(tableThreat, control.textQuery.THREATS);
        //Third table - pentest results
        findData(tablePentest, control.textQuery.PENTEST);
        control.createThreatList();
    }
    
    //Find data for field data and columns
    private void findData(TablesView view, MainController.TextQuery param) {
        view.data = control.findRecord(param);
        view.columns = columnsName(param);
        view.fillTable();
    }
    
     private void loadLevelsComboBox() {
        control.loadComboBox(control.textQuery.SECURITYLEVEL, LevelComboBox);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LevelSecurityScrollPane = new javax.swing.JScrollPane();
        LevelSecurityTable = new javax.swing.JTable();
        LevelComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ThreatTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PentestTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        SecurityLevelButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

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
        LevelSecurityTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        LevelSecurityScrollPane.setViewportView(LevelSecurityTable);

        LevelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevelComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Группа");

        ThreatTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(ThreatTable);

        jLabel2.setText("Укажите % вероятности принадлежности ИС к группам безопасности");

        jLabel3.setText("По каждой уязвимости укажите % вероятности ее принадлежности к группам");

        PentestTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(PentestTable);

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setAlignmentX(1.5F);
        jSeparator1.setAlignmentY(1.5F);
        jSeparator1.setAutoscrolls(true);
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 50));
        jSeparator1.setRequestFocusEnabled(false);

        SecurityLevelButton.setText("Определить");
        SecurityLevelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SecurityLevelButtonActionPerformed(evt);
            }
        });

        BackButton.setText("Назад");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Определение уровня безопасности ИС данного предприятия");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LevelSecurityScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))))
                .addGap(44, 44, 44)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SecurityLevelButton)
                .addGap(27, 27, 27)
                .addComponent(BackButton)
                .addGap(204, 204, 204))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SecurityLevelButton)
                    .addComponent(BackButton))
                .addGap(147, 147, 147))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(LevelSecurityScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(221, 221, 221))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LevelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LevelComboBoxActionPerformed

    private void SecurityLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecurityLevelButtonActionPerformed
        //addCommonPercent();
    }//GEN-LAST:event_SecurityLevelButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        control.hideSecurityLevelPanel();
        control.showPanels();
    }//GEN-LAST:event_BackButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JComboBox LevelComboBox;
    private javax.swing.JScrollPane LevelSecurityScrollPane;
    private javax.swing.JTable LevelSecurityTable;
    private javax.swing.JTable PentestTable;
    private javax.swing.JButton SecurityLevelButton;
    private javax.swing.JTable ThreatTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
