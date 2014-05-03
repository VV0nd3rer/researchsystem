package view;

import controller.MainController;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SecurityLevePanel extends JPanel implements ITableView {

    private MainController control = null;
//    private Vector data = null;
//    private Vector columns = null;
    
    public SecurityLevePanel(String enterpriseName) {
        initComponents();
        control = MainController.getInstance();
        enterpriseLabel.setText(enterpriseName);
        showTables();
        addChangeListener(control.textQuery.SECURITYLEVEL);
        addSelectListener(control.textQuery.SECURITYLEVEL);
        addChangeListener(control.textQuery.THREATS);
        addChangeListener(control.textQuery.PENTEST);
    }
    //------------------ ITable interfaces methods ---------------------------//
    public void addChangeListener(final MainController.TextQuery type) {
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
        int row = e.getFirstRow(); 
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        if (column != -1) { 
            Integer num = (Integer)model.getValueAt(row, 0);
            switch (type) {
                case SECURITYLEVEL:
                    control.addCommonPercent(num, Float.parseFloat(model.getValueAt(row, column).toString()));
                    break;
                case THREATS:
                    control.addThreatPercent(num, Float.parseFloat(model.getValueAt(row, column).toString()));
                    break;
                case PENTEST:
                    control.addPentestResult(num, Boolean.parseBoolean(model.getValueAt(row, column).toString()));
                    break;
                default:
                    break;                  
            }
        }
    }
    public void selectRow(ListSelectionEvent e, MainController.TextQuery type) {
         if (e.getValueIsAdjusting())
             return;
         JTable table = determineTable(type);
         int row = table.getSelectedRow();
         control.setSelectedLevel((int)table.getValueAt(row, 0));
         //Edit for many diffrent tables
         if (type.equals(control.textQuery.SECURITYLEVEL))
             updateTable(ThreatTable, ThreatTable.getColumnCount()-1, control.loadThreatPercent(table));
    }
    public void fillTable(JTable table, Vector data, Vector columns) {
      table.setModel(new DefaultTableModel(data, columns));
      //defaultTableModel = (DefaultTableModel)AuditTable.getModel();
    }
    public JTable determineTable(MainController.TextQuery type) {
         JTable table = null;
        switch(type) {
           case SECURITYLEVEL:
                table = LevelSecurityTable;
                break;
           case THREATS:
                table = ThreatTable;
                break;
           case PENTEST:
                table = PentestTable;
                break;
            default:
                break;
        }
        return table;
    }
    //------------------------------------------------------------------//
    
    //Update column in table
    public void updateTable(JTable table, int column, Vector data) {
        //System.out.println(data);
        for (int i = 0; i < data.size(); i++) {
            table.setValueAt(data.get(i), i, column);
        }
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
        findData(LevelSecurityTable, control.textQuery.SECURITYLEVEL);
        //Second table - threat level
        findData(ThreatTable, control.textQuery.THREATS);
        //Third table - pentest results
        findData(PentestTable, control.textQuery.PENTEST);
        control.createThreatList();
        control.createPentestList();
        //resultTable.setVisible(false);
    }
    private void showResults() {
        Vector threats = control.findRecord(control.textQuery.THREATS);
        Vector statistics = new Vector();
        statistics.add("Common statistics");
        threats = control.getTableHeader(threats);
        for (int i = 0; i < threats.size(); i++) {
            statistics.add(threats.get(i));
        }
        fillVerticalHeader(ResultTable, statistics, control.getThreatCount());
        Vector securtiyLevel = control.findRecord(control.textQuery.SECURITYLEVEL);
        fillHorizontalHeader(ResultTable, control.getTableHeader(securtiyLevel), control.getLevelCount());
        fillTable(ResultTable, control.showResults());
    }
    //Find data for field data and columns
    private void findData(JTable table, MainController.TextQuery param) {
        Vector data = control.findRecord(param);
        Vector columns = columnsName(param);
        fillTable(table, data, columns);
    }
    private void fillVerticalHeader(JTable table, Vector data, int rowCount) {
        DefaultTableModel defaultTable = (DefaultTableModel)table.getModel();
        defaultTable.setRowCount(rowCount);
        for (int i = 0; i < data.size(); i++) {
           table.setValueAt(data.get(i), i, 0);  
        }
        table.getColumnModel().getColumn(0).setCellRenderer(new RowHeaderRenderer());
    }
    private void fillHorizontalHeader(JTable table, Vector data, int columnCount) {
         DefaultTableModel defaultTable = (DefaultTableModel)table.getModel();
         defaultTable.setColumnCount(columnCount + 1);
         table.getColumnModel().getColumn(0).setHeaderValue("");
         for(int i = 0; i < data.size(); i++) {
             //System.out.println("fillHorizontalHeader" + data.get(i));
             table.getColumnModel().getColumn(i+1).setHeaderValue(data.get(i));
         }
    }
    private void fillTable(JTable table, Vector data) {
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < ((Vector)data.get(i)).size(); j++) {
                table.setValueAt(((Vector)data.get(i)).get(j), i, j+1);
            }
        }
    }
    private void saveResult() {
        Vector data = new Vector<Integer>();
        System.out.println(control.getEnterpriseId());
        data.add(control.getEnterpriseId());
        System.out.println(control.getResultLevel());
        data.add(control.getResultLevel());
        control.createRecord(data, MainController.TextQuery.AUDITCLIENT);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LevelSecurityScrollPane = new javax.swing.JScrollPane();
        LevelSecurityTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        ThreatTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PentestTable = new JTable() {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    return String.class;
                    case 1:
                    return String.class;
                    case 2:
                    return Boolean.class;
                    default:
                    return getValueAt(0, column).getClass();
                }
            }
        };
        jSeparator1 = new javax.swing.JSeparator();
        SecurityLevelButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        enterpriseLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ResultTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Определение уровня безопасности ИС данного предприятия");

        enterpriseLabel.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        enterpriseLabel.setText("jLabel5");

        ResultTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(ResultTable);

        jLabel1.setText("Укажите, была ли реализована каждая из угроз в процессе проведения теста на проникновение");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(enterpriseLabel)
                .addGap(182, 182, 182)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LevelSecurityScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(SecurityLevelButton)
                        .addGap(18, 18, 18)
                        .addComponent(BackButton))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enterpriseLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(LevelSecurityScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BackButton)
                            .addComponent(SecurityLevelButton))))
                .addGap(146, 146, 146))
        );

        enterpriseLabel.getAccessibleContext().setAccessibleName("enterpriseLabel");
    }// </editor-fold>//GEN-END:initComponents

    private void SecurityLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecurityLevelButtonActionPerformed
        control.determineSecurityLevel();
        showResults();
        saveResult();
    }//GEN-LAST:event_SecurityLevelButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        control.hideSecurityLevelPanel();
        control.showPanels();
    }//GEN-LAST:event_BackButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JScrollPane LevelSecurityScrollPane;
    private javax.swing.JTable LevelSecurityTable;
    private javax.swing.JTable PentestTable;
    private javax.swing.JTable ResultTable;
    private javax.swing.JButton SecurityLevelButton;
    private javax.swing.JTable ThreatTable;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
