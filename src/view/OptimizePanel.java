package view;

import javax.swing.JPanel;
import controller.MainController;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class OptimizePanel extends JPanel implements ITableView {
    private MainController control = null;
    public DefaultTableModel defaultTableModel = null;
    public OptimizePanel() {
        initComponents();
        control = MainController.getInstance();
        showTable();
        addSelectListener(MainController.TextQuery.DLPSYSTEMS);
    }

     //------------------ ITable interfaces methods ---------------------------//
    public void addChangeListener (final MainController.TextQuery type) {
        determineTable(type).getModel().addTableModelListener(new TableModelListener() {
            @Override
             public void tableChanged(TableModelEvent e) {
                changeRow(e, type);
             }
        });
    }
    public void addSelectListener(final MainController.TextQuery type) {
        determineTable(type).getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               selectRow(e, type);
            }
        });
    }
    public void changeRow(TableModelEvent e, MainController.TextQuery type) {
        System.out.println("changeRow method");
    }
    public void selectRow(ListSelectionEvent e, MainController.TextQuery type) {
        if (e.getValueIsAdjusting())
             return;
         JTable table = determineTable(type);
         int row = table.getSelectedRow();
         int dlpId = (int)table.getValueAt(row, 0);
         System.out.println("dlpId: " + dlpId);
         //control.setSelectedLevel((int)table.getValueAt(row, 0));
         //Edit for many diffrent tables
         if (type.equals(control.textQuery.DLPSYSTEMS))
             updateTable(DlpEstimatesTable, DlpEstimatesTable.getColumnCount()-1, control.getDlpEstimates(dlpId));
        System.out.println("selectRow method");
    }
    public void fillTable(JTable table, Vector data, Vector columns) {
      table.setModel(new DefaultTableModel(data, columns));
      defaultTableModel = (DefaultTableModel)table.getModel();
    }
    public JTable determineTable(MainController.TextQuery type) {
         JTable table = null;
        switch(type) {
            case CRITERIAESTIMATE:
                    table = CriteriasEstimateTable;
                    break;
            case DLPESTIMATE:
                    table = DlpEstimatesTable;
                    break;
            case DLPSYSTEMS:
                    table = DlpTable;
                    break;
            default:
                    break;  
        }
        return table;
    }
    //------------------------------------------------------------------//
     public void updateTable(JTable table, int column, Vector data) {
        //System.out.println(data);
        fillTable(table, data, columnsName(control.textQuery.DLPESTIMATE));
    }
    private Vector columnsName(MainController.TextQuery _table) {
        Vector columnNames = new Vector();
        switch (_table) 
        {
            case CRITERIAESTIMATE:
                 columnNames.addElement("Num");
                 columnNames.addElement("Linguistic estimate");
                 columnNames.addElement("Fuzzy estimate");
                 break;
            case DLPSYSTEMS:
                 columnNames.addElement("Num");
                 columnNames.addElement("Title");
                 columnNames.addElement("Information");
                 break;
            case DLPESTIMATE:
                columnNames.addElement("System num");
                columnNames.addElement("Criteria num");
                columnNames.addElement("Linguistic estimate");
                columnNames.addElement("Fuzzy estimate");
                break;
            default:
                break;
        }
         return columnNames;
    }
     private void showTable() { 
         fillTable(CriteriasEstimateTable, control.determineCriteriasEstimates(), columnsName(control.textQuery.CRITERIAESTIMATE));
         fillTable(DlpTable, control.getResearchDlp(1), columnsName(MainController.TextQuery.DLPSYSTEMS));
         control.determineDlpEstimates();
         fillTable(DlpEstimatesTable, control.getDlpEstimates(1), columnsName(control.textQuery.DLPESTIMATE));
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        CriteriasEstimateTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DlpTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DlpEstimatesTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        CriteriasEstimateTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(CriteriasEstimateTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Competence estimates of criterias");

        DlpTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(DlpTable);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Competence estimates if DLP-systems");

        DlpEstimatesTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(DlpEstimatesTable);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Optimize");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(jButton2)
                        .addGap(36, 36, 36)
                        .addComponent(jButton1))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(259, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        control.hideOptimizePanel();
        control.showPanels();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        control.createResearch();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CriteriasEstimateTable;
    private javax.swing.JTable DlpEstimatesTable;
    private javax.swing.JTable DlpTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
