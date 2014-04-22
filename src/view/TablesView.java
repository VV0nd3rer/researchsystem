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
     public Vector data = null;
     public Vector columns = null;
     ListSelectionModel selectionModel = null;
     private MainController control = null;
 
     public TablesView(JTable _table) {
         control = MainController.getInstance();
         table = _table;
     }
     
      public void addModelListener(final MainController.TextQuery type) {
          //DLPModel.addTableModelListener(this);    
         table.getModel().addTableModelListener(new TableModelListener() {

             @Override
             public void tableChanged(TableModelEvent e) {
                changeRow(e, type);
             }
         });
     }
       public void changeRow(TableModelEvent e, MainController.TextQuery type) {
                int row = e.getFirstRow(); 
                int column = e.getColumn();
                TableModel model = (TableModel)e.getSource();
                if (column != -1) { 
                    Float res = Float.parseFloat(model.getValueAt(row, column).toString());
                    Integer num = (Integer)model.getValueAt(row, 0);
                    switch (type) {
                        case SECURITYLEVEL:
                            control.addCommonPercent(num, res);
                            break;
                        case THREATS:
                            control.addThreatPercent(num, res);
                            break;
                        case PENTEST:
                            //
                            break;
                        default:
                            break;                  
                    }
                    System.out.println("Res: " + res.toString()); 
                    System.out.println("Num: " + num.toString());     
        }
   }
     
     public void addSelectionListener() {
         table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
              public void valueChanged(ListSelectionEvent e) {
                selectValue(e);
            }       
          });
     }
     
     public void selectValue(ListSelectionEvent e) {
         if (e.getValueIsAdjusting())
             return;
         int row = table.getSelectedRow();
         control.setSelectedLevel((int)table.getValueAt(row, 0));
         //fillTable(table.getColumnCount()-1, control.loadThreatPercent(), table);
         System.out.println("Selected num of security level: " + table.getValueAt(row, 0).toString());
      }
     public void fillTable() {
         table.setModel(new DefaultTableModel(data, columns));
         DLPModel = (DefaultTableModel)table.getModel();
     }
     public void fillTable(int column, Vector data, JTable table) {
         for (int i =0; i < data.size(); i++) {
             table.setValueAt(data.get(i), i, column);
         }
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
