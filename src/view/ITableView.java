package view;

//TableModelEvent is used to notify listeners that a table model has changed.
import controller.MainController;
import javax.swing.event.TableModelEvent;
//An event that characterizes a change in selection.
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTable;

import java.util.Vector;

 interface ITableView {
     
     void addChangeListener(MainController.TextQuery type);
     void addSelectListener(MainController.TextQuery type);
     
     void changeRow(TableModelEvent e, MainController.TextQuery type);
     void selectRow(ListSelectionEvent e, MainController.TextQuery type);
     
     void fillTable(JTable table, Vector data, Vector columns);
     JTable determineTable(MainController.TextQuery type);
//     void createRow();
//     void updateRow();
//     void deleteRow();
}
