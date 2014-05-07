package view;

import controller.MainController;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
public class ResearchPanel extends JPanel implements ITableView {
    private MainController control = null;
    public DefaultTableModel defaultTableModel = null;
    public ResearchPanel() {
        control = MainController.getInstance();
        initComponents();
        showTable();
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
        System.out.println("selectRow method");
    }
    public void fillTable(JTable table, Vector data, Vector columns) {
      table.setModel(new DefaultTableModel(data, columns));
      defaultTableModel = (DefaultTableModel)table.getModel();
    }
    public JTable determineTable(MainController.TextQuery type) {
        return ResearchTable;
    }
    //------------------------------------------------------------------//
    private Vector columnsName() {
        Vector columnNames = new Vector();     
         columnNames.addElement("Num");
         columnNames.addElement("Enterprise");
         columnNames.addElement("Best DLP");
         columnNames.addElement("Research done");
         return columnNames;
    }
     private void showTable() {
         Vector data = control.findRecord(control.textQuery.RESEARCH);
         Vector columns = columnsName();
         fillTable(ResearchTable, data, columns);
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
        //control.setCriteriasEstimates();
        
    }//GEN-LAST:event_OkButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OkButton;
    private javax.swing.JScrollPane ResearchScrollPane;
    private javax.swing.JTable ResearchTable;
    // End of variables declaration//GEN-END:variables
}
