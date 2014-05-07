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
         JTable table = null;
        switch(type) {
            case CRITERIAESTIMATE:
                    table = CriteriasEstimate;
                    break;
            case DLPESTIMATE:
                    table = DlpEstimates;
                    break;
            default:
                    break;  
        }
        return table;
    }
    //------------------------------------------------------------------//
    private Vector columnsName() {
        Vector columnNames = new Vector();     
         columnNames.addElement("Num");
         columnNames.addElement("Linguistic estimate");
         columnNames.addElement("Fuzzy estimate");
         return columnNames;
    }
     private void showTable() {
         Vector data = control.setCriteriasEstimates();
         Vector columns = columnsName();
         fillTable(CriteriasEstimate, data, columns);
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
        CriteriasEstimate = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DlpEstimates = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        CriteriasEstimate.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(CriteriasEstimate);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Competence estimates of criterias");

        DlpEstimates.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(DlpEstimates);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Competence estimates if DLP-systems");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap(446, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CriteriasEstimate;
    private javax.swing.JTable DlpEstimates;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}