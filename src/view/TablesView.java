package view;

import java.util.Vector;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.*;
import controller.MainController;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//Class for render row column like a header in the table
  class RowHeaderRenderer extends DefaultTableCellRenderer {
        public RowHeaderRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            if (table != null) {
                JTableHeader header = table.getTableHeader();

                if (header != null) {
                    setForeground(header.getForeground());
                    setBackground(header.getBackground());
                    setFont(header.getFont());
                }
            }

            if (isSelected) {
                setFont(getFont().deriveFont(Font.BOLD));
            }

            setValue(value);
            return this;
        }
    }

public class TablesView {
     private JTable table = null;
     public DefaultTableModel DLPModel = null;
     public Vector changeNum = new Vector();
     public  Vector data = null;
     ListSelectionModel selectionModel = null;
     private MainController control = null;
 
     public TablesView(JTable _table) {
         control = MainController.getInstance();
         table = _table;
     }
     
     //For tables with need id for changed
     public void addIdModel() {
          //DLPModel.addTableModelListener(this);    
         table.getModel().addTableModelListener(new TableModelListener() {

             @Override
             public void tableChanged(TableModelEvent e) {
                tableIdChanged(e);
             }
         });
     }
     
     //For tables with other ways to change data
     public void addOtherModel() {
         table.getModel().addTableModelListener(new TableModelListener() {

             @Override
             public void tableChanged(TableModelEvent e) {
                 tableOtherChanged(e);
             }
         });
     }
    
     public void addListener() {
         selectionModel = table.getSelectionModel();
         selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectValue(e);
            }       
          });
     }
     
     public void selectValue(ListSelectionEvent e) {
         int row = table.getSelectedRow();
         System.out.println("Selected dlp: " + table.getValueAt(row, 2).toString());
         control.setAuditSelected((Integer)table.getValueAt(row, 2));
      }

     public void tableIdChanged(TableModelEvent e) {
                int row = e.getFirstRow(); 
                int column = e.getColumn();
                TableModel model = (TableModel)e.getSource();
                if (column != -1) { 
                    Object res = model.getValueAt(row, column);
                    if (model.getValueAt(row, 0) == "")
                        changeNum.add("");
                    else
                        changeNum.add(((Vector)data.get(row)).get(0));
                    System.out.println("Before add data: " + data.get(row).toString());
                    ((Vector)data.get(row)).set(column, res);
                    System.out.println("Added data: " + data.get(row).toString());      
        }
   }
     
     public void tableOtherChanged(TableModelEvent e) {
         System.out.println("oops, but name of this function is very bad :(");
     }
     
     public void fillTable(Vector _columnsNames) {
         table.setModel(new DefaultTableModel(data, _columnsNames));
         DLPModel = (DefaultTableModel)table.getModel();
     }
     
     public void fillColumnHeadTable(Vector _col) {
         DLPModel = (DefaultTableModel)table.getModel();
         table.getColumnModel().getColumn(0).setHeaderValue("");
         for(int i = 0; i < _col.size(); i++) {
             table.getColumnModel().getColumn(i+1).setHeaderValue(_col.get(i));
         }
     }
     
     public void fillRowHeadTable() {
        DLPModel = (DefaultTableModel)table.getModel();
        DLPModel.setRowCount(8);        
        for (int i = 0; i < data.size(); i++) {
           table.setValueAt(data.get(i), i, 0);  
        }
        table.getColumnModel().getColumn(0).setCellRenderer(new RowHeaderRenderer());
     }

     public void deleteRow() {
         DLPModel.removeRow(table.getSelectedRow());
     }
     
     public void addRow() {
           DLPModel.addRow( new Object[] {"","","","",""});
     }
     
}
