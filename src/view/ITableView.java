package view;

//TableModelEvent is used to notify listeners that a table model has changed.
import javax.swing.event.TableModelEvent;
//An event that characterizes a change in selection.
import javax.swing.event.ListSelectionEvent;

 interface ITableView {
     
     void addChangeListener();
     void addSelectListener();
     
     void changeRow(TableModelEvent e);
     void selectRow(ListSelectionEvent e);
     
     void fillTable();
//     void createRow();
//     void updateRow();
//     void deleteRow();
}
