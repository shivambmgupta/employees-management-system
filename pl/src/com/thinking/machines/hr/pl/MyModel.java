package com.thinking.machines.hr.pl;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.math.*;
import java.util.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.manager.*;
public class MyModel extends AbstractTableModel{
private String[] title={"S.No","Name","Code"};
private Object data[][];
private java.util.List<EmployeeInterface> employeeList;
private int rows,columns;
MyModel(){
populateTable();
}
private void populateTable(){
EmployeeManager empManager=new EmployeeManager();
rows=empManager.getNumberOfEmployees();
columns=3;
employeeList=empManager.getAll(EmployeeManager.NAME);
data=new Object[rows][3];
for(int i=0;i<rows;++i){
data[i][0]=i+1;
data[i][1]=employeeList.get(i).getName();
data[i][2]=employeeList.get(i).getCode();
}
}
public int getColumnCount(){
return columns;
}
public int getRowCount(){
return rows;
}
public String getColumnName(int columnIndex){
return title[columnIndex];
}
public boolean isCellEditable(int rowIndex, int columnIndex){
return false;
}
public Object getValueAt(int  rowIndex,int columnIndex){
return data[rowIndex][columnIndex];
}
}
