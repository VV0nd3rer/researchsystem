package view;

import controller.MainController;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;
import javax.swing.*;
//TableModelEvent is used to notify listeners that a table model has changed.
import javax.swing.event.TableModelEvent;
//TableModelListener defines the interface for an object that listens to changes in a TableModel.
import javax.swing.event.TableModelListener;
//The standard class for rendering (displaying) individual cells in a JTable. 
//import javax.swing.table.DefaultTableCellRenderer;
//This is an implementation of TableModel that uses a Vector of Vectors to store the cell value objects. 
import javax.swing.table.DefaultTableModel;
//This interface represents the current state of the selection for any of the components that display a list of values with stable indices. 
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class AuditPanel extends JPanel implements ITableView {
    private MainController control = null;
    public DefaultTableModel defaultTableModel = null;
    
    public Vector data = null;
    public Vector columns = null;
    
    public AuditPanel() {
        control = MainController.getInstance();
        initComponents();
        //tableView = new TablesView(AuditTable);
        showTable();
        loadEnterprisesComboBox();
        addChangeListener();
        addSelectListener();
        //tableView.addIdModel();
        //tableView.addListener();
    }
    
    //------------------ ITable interfaces methods ---------------------------//
    public void addChangeListener() {
        AuditTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
             public void tableChanged(TableModelEvent e) {
                changeRow(e);
             }
        });
    }
    public void addSelectListener() {
        AuditTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               selectRow(e);
            }
        });
    }
    public void changeRow(TableModelEvent e) {
        System.out.println("changeRow method");
    }
    public void selectRow(ListSelectionEvent e) {
        System.out.println("selectRow method");
    }
    public void fillTable() {
      AuditTable.setModel(new DefaultTableModel(data, columns));
      defaultTableModel = (DefaultTableModel)AuditTable.getModel();
    }
    //------------------------------------------------------------------//
    
    private Vector columnsName() {
        Vector columnNames = new Vector();     
         columnNames.addElement("Num");
         columnNames.addElement("Name");
         columnNames.addElement("Level");
         return columnNames;
    }
    
     private void showTable() {
         data = control.findRecord(control.textQuery.AUDITCLIENT);
         columns = columnsName();
         fillTable();
    }
    
    private void loadEnterprisesComboBox() {
        control.loadComboBox(control.textQuery.ENTERPRISES, EnterprisesComboBox);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AuditScrollPane = new javax.swing.JScrollPane();
        AuditTable = new javax.swing.JTable();
        AuditLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        EnterprisesComboBox = new javax.swing.JComboBox();

        setMaximumSize(null);

        AuditTable.setModel(new javax.swing.table.DefaultTableModel(
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
        AuditScrollPane.setViewportView(AuditTable);

        AuditLabel.setText("Audits");

        jLabel1.setText("Новый аудит");

        EnterprisesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnterprisesComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AuditScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(AuditLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EnterprisesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addContainerGap(339, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(AuditLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AuditScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(EnterprisesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(235, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EnterprisesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnterprisesComboBoxActionPerformed
      System.out.println(control.getComboBoxId(evt, EnterprisesComboBox));
      System.out.println(control.getComboBoxValue(evt, EnterprisesComboBox));
    }//GEN-LAST:event_EnterprisesComboBoxActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AuditLabel;
    private javax.swing.JScrollPane AuditScrollPane;
    private javax.swing.JTable AuditTable;
    private javax.swing.JComboBox EnterprisesComboBox;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

// class Item {
//        private int id;
//        private String description;
//        public Item(int id, String description) {
//            this.id = id;
//            this.description = description;
//        }
//        public int getId() {
//            return id;
//        }
//        public String getDescription() {
//            return description;
//        }
//        public String toString() {
//            return description;
//        }
//}