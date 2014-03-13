package view;

import java.util.Vector;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JTable;

public class TablesView implements TableModelListener {
     private JTable table = null;
     public DefaultTableModel DLPModel = null;
     public Vector changeNum = new Vector();
     public  Vector data = null;
     
     public TablesView(JTable _table) {
         table = _table;
     }
     
     public void addModel() {
          DLPModel.addTableModelListener(this);
     }
        
     public void tableChanged(TableModelEvent e) {
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
     
     public void fillTable(Vector _columnsNames) {
         table.setModel(new DefaultTableModel(data, _columnsNames));
         DLPModel = (DefaultTableModel)table.getModel();
     }
     
     public void deleteRow() {
         DLPModel.removeRow(table.getSelectedRow());
     }
     
     public void addRow() {
           DLPModel.addRow( new Object[] {"","","","",""});
     }
     
}
