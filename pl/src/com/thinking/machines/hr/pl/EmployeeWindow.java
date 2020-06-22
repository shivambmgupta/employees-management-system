package com.thinking.machines.hr.pl;
import java.util.*;
import java.math.*;
import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.manager.*;
import com.thinking.machines.hr.bl.pojo.*;
public class EmployeeWindow extends JFrame implements ActionListener,MouseListener{
private JLabel searchLabel,employeesLabel,notFoundLabel;
private ImageIcon addIcon,saveIcon,editIcon,deleteIcon,undoIcon,saveToPDFIcon,searchIcon,searchCancelIcon;
private JTextField searchField,nameField,panNumberField,salaryField;
private JLabel codeLabel,nameLabel,salaryLabel,genderLabel,panNumberLabel;
private JLabel vCodeLabel,vNameLabel,vSalaryLabel,vGenderLabel,vPanNumberLabel;
private Container container;
private JTable employeesTable;
private JScrollPane jsp;
private JComboBox genderComboBox;
private MyModel myModel;
private JButton saveButton,addButton,editButton,undoButton,deleteButton,saveToPDFButton,editSaveButton;
private JButton searchCancelButton,searchButton;
private JPanel namePanel,genderPanel,salaryPanel,panNumberPanel,addSavePanel,editSavePanel;
private CardLayout cardLayout;

public EmployeeWindow(){
super("Employee Managment System");
ImageIcon appIcon=new ImageIcon("c:/hr/pl/src/com/thinking/machines/hr/pl/appIcon.png");
setIconImage(appIcon.getImage());
container=getContentPane();
myModel=new MyModel();
employeesTable=new JTable(myModel);
employeesTable.setFont(new Font("Verdana",Font.PLAIN,16));
employeesTable.setRowHeight(30);
jsp=new JScrollPane(employeesTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
searchLabel=new JLabel("Search: ");
addIcon=new ImageIcon("c:/hr/pl/src/com/thinking/machines/hr/pl/addIcon.png");
editIcon=new ImageIcon("c:/hr/pl/src/com/thinking/machines/hr/pl/editIcon.png");
deleteIcon=new ImageIcon("c:/hr/pl/src/com/thinking/machines/hr/pl/deleteIcon.png");
undoIcon=new ImageIcon("c:/hr/pl/src/com/thinking/machines/hr/pl/undoIcon.png");
searchIcon=new ImageIcon("c:/hr/pl/src/com/thinking/machines/hr/pl/searchIcon.png");
searchCancelIcon=new ImageIcon("c:/hr/pl/src/com/thinking/machines/hr/pl/searchCancelIcon.png");
saveToPDFIcon=new ImageIcon("c:/hr/pl/src/com/thinking/machines/hr/pl/saveToPDF.png");
saveIcon=new ImageIcon("c:/hr/pl/src/com/thinking/machines/hr/pl/saveIcon.png");
codeLabel=new JLabel("Code");
nameField=new JTextField(30);
salaryField=new JTextField(30);
panNumberField=new JTextField(30);
nameLabel=new JLabel("Name");
salaryLabel=new JLabel("Salary");
genderLabel=new JLabel("Gender");
panNumberLabel=new JLabel("PAN Number");
vCodeLabel=new JLabel("");
vNameLabel=new JLabel("");
vSalaryLabel=new JLabel("");
vGenderLabel=new JLabel("");
vPanNumberLabel=new JLabel("");
notFoundLabel=new JLabel("");
employeesLabel=new JLabel("Ems");
searchField=new JTextField(17);
addButton=new JButton(addIcon);
undoButton=new JButton(undoIcon);
editButton=new JButton(editIcon);
deleteButton=new JButton(deleteIcon);
saveToPDFButton=new JButton(saveToPDFIcon);
searchCancelButton=new JButton(searchCancelIcon);
searchButton=new JButton(searchIcon);
saveButton=new JButton(saveIcon);
editSaveButton=new JButton(saveIcon);
cardLayout=new CardLayout();
addSavePanel=new JPanel(cardLayout);
cardLayout.addLayoutComponent(addButton,"addButton");
cardLayout.addLayoutComponent(saveButton,"saveButton");
addSavePanel.add(addButton);
addSavePanel.add(saveButton);
cardLayout.show(addSavePanel,"addButton");

editSavePanel=new JPanel(cardLayout);
cardLayout.addLayoutComponent(editButton,"editButton");
cardLayout.addLayoutComponent(editSaveButton,"editSaveButton");
editSavePanel.add(editButton);
editSavePanel.add(editSaveButton);
cardLayout.show(editSavePanel,"editButton");

genderComboBox=new JComboBox();
genderComboBox.addItem("Male");
genderComboBox.addItem("Female");
addButton.setEnabled(true);
undoButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(true);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);

addButton.addActionListener(this);
saveButton.addActionListener(this);
undoButton.addActionListener(this);
editButton.addActionListener(this);
deleteButton.addActionListener(this);
saveToPDFButton.addActionListener(this);
searchCancelButton.addActionListener(this);
searchButton.addActionListener(this);
editSaveButton.addActionListener(this);
searchField.addMouseListener(this);
employeesTable.addMouseListener(this);

container.add(employeesLabel);
JPanel headerPanel=new JPanel(new FlowLayout());
JPanel searchPanel=new JPanel(new FlowLayout());
headerPanel.add(employeesLabel);
headerPanel.add(new JLabel(""));
headerPanel.add(new JLabel(""));
headerPanel.add(new JLabel(""));
headerPanel.add(notFoundLabel);
searchPanel.add(searchLabel);
searchPanel.add(searchField);
searchPanel.add(searchCancelButton);
searchPanel.add(searchButton);
container.add(headerPanel);
container.add(searchPanel);
container.add(jsp);
JPanel employeeDetailsDisplayPanel=new JPanel(new GridLayout(5,2));
employeeDetailsDisplayPanel.add(codeLabel);
employeeDetailsDisplayPanel.add(vCodeLabel);
employeeDetailsDisplayPanel.add(nameLabel);
namePanel=new JPanel(cardLayout);
namePanel.add(vNameLabel);
namePanel.add(nameField);
cardLayout.addLayoutComponent(vNameLabel,"vNameLabel");
cardLayout.addLayoutComponent(nameField,"nameField");
cardLayout.show(namePanel,"vNameLabel");
employeeDetailsDisplayPanel.add(namePanel);

employeeDetailsDisplayPanel.add(panNumberLabel);
panNumberPanel=new JPanel(cardLayout);
panNumberPanel.add(vPanNumberLabel);
panNumberPanel.add(panNumberField);
cardLayout.addLayoutComponent(vPanNumberLabel,"vPanNumberLabel");
cardLayout.addLayoutComponent(panNumberField,"panNumberField");
cardLayout.show(panNumberPanel,"vPanNumberLabel");
employeeDetailsDisplayPanel.add(panNumberPanel);

employeeDetailsDisplayPanel.add(genderLabel);
genderPanel=new JPanel(cardLayout);
genderPanel.add(vGenderLabel);
genderPanel.add(genderComboBox);
cardLayout.addLayoutComponent(vGenderLabel,"vGenderLabel");
cardLayout.addLayoutComponent(genderComboBox,"genderComboBox");
cardLayout.show(genderPanel,"vGenderLabel");
employeeDetailsDisplayPanel.add(genderPanel);

employeeDetailsDisplayPanel.add(salaryLabel);
salaryPanel=new JPanel(cardLayout);
salaryPanel.add(vSalaryLabel);
salaryPanel.add(salaryField);
cardLayout.addLayoutComponent(vSalaryLabel,"vSalaryLabel");
cardLayout.addLayoutComponent(salaryField,"salaryField");
cardLayout.show(salaryPanel,"vSalaryLabel");
employeeDetailsDisplayPanel.add(salaryPanel);
container.add(employeeDetailsDisplayPanel);

JPanel bottomButtons=new JPanel();
bottomButtons.add(addSavePanel);
bottomButtons.add(editSavePanel);
bottomButtons.add(undoButton);
bottomButtons.add(deleteButton);
bottomButtons.add(saveToPDFButton);
container.add(bottomButtons);

setLayout(new GridLayout(5,1));
setDefaultCloseOperation(EXIT_ON_CLOSE);
setSize(500,500);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width/2-200,d.height/2-150);
setVisible(true);
}
public void actionPerformed(ActionEvent e){
JComponent component=(JComponent)e.getSource();
if(component==addButton){
cardLayout.show(addSavePanel,"saveButton");
employeesTable.setEnabled(false);
undoButton.setEnabled(true);
searchField.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(false);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);
employeesTable.setEnabled(false);
cardLayout.show(namePanel,"nameField");
cardLayout.show(salaryPanel,"salaryField");
cardLayout.show(genderPanel,"genderComboBox");
cardLayout.show(panNumberPanel,"panNumberField");
}
if(component==saveButton){
EmployeeInterface employee=new Employee();
employee.setCode(0);
employee.setName(nameField.getText());
if(genderComboBox.getSelectedItem()=="Male")
employee.setGender("Male");
else
employee.setGender("Female");
employee.setSalary(new BigDecimal(salaryField.getText()));
employee.setPANNumber(panNumberField.getText());
try{
EmployeeManagerInterface employeeManager=new EmployeeManager();
employeeManager.addEmployee(employee);
JOptionPane.showMessageDialog(this,"Employee Added");
}catch(ValidationException ve){
JOptionPane.showMessageDialog(this,"Validation Problem");
}
catch(ProcessException pe){
JOptionPane.showMessageDialog(this,"Process Halted");
}
catch(Exception ee){
JOptionPane.showMessageDialog(this,"Dont know what happened");
}
employeesTable.setEnabled(true);
cardLayout.show(addSavePanel,"addButton");
undoButton.setEnabled(false);
searchField.setEnabled(true);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(true);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);
nameField.setText("");
salaryField.setText("");
panNumberField.setText("");
cardLayout.show(namePanel,"vNameLabel");
cardLayout.show(salaryPanel,"vSalaryLabel");
cardLayout.show(genderPanel,"vGenderLabel");
cardLayout.show(panNumberPanel,"vPanNumberLabel");
myModel=new MyModel();
employeesTable.setModel(myModel);
}
if(component==undoButton){
cardLayout.show(addSavePanel,"addButton");
cardLayout.show(editSavePanel,"editButton");
employeesTable.setEnabled(true);
employeesTable.clearSelection();
vCodeLabel.setText("");
vNameLabel.setText("");
vGenderLabel.setText("");
vPanNumberLabel.setText("");
vSalaryLabel.setText("");
addButton.setEnabled(true);
undoButton.setEnabled(false);
searchField.setEnabled(true);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(true);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);
employeesTable.setEnabled(true);
nameField.setText("");
panNumberField.setText("");
salaryField.setText("");
cardLayout.show(namePanel,"vNameLabel");
cardLayout.show(salaryPanel,"vSalaryLabel");
cardLayout.show(genderPanel,"vGenderLabel");
cardLayout.show(panNumberPanel,"vPanNumberLabel");
}
if(component==searchCancelButton){
addButton.setEnabled(true);
searchField.setText("");
editButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(true);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);
employeesTable.setEnabled(true);
}
if(component==deleteButton){
addButton.setEnabled(false);
editButton.setEnabled(false);
undoButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(false);
searchField.setEnabled(false);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);
employeesTable.setEnabled(false);
try{
new EmployeeManager().deleteEmployee(Integer.parseInt(vCodeLabel.getText()));
JOptionPane.showMessageDialog(this,"Employee record deleted");
}catch(Exception ee){}
addButton.setEnabled(true);
editButton.setEnabled(false);
undoButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(true);
searchField.setEnabled(true);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);
employeesTable.setEnabled(true);
vCodeLabel.setText("");
vNameLabel.setText("");
vGenderLabel.setText("");
vPanNumberLabel.setText("");
vSalaryLabel.setText("");
employeesTable.clearSelection();
myModel=new MyModel();
employeesTable.setModel(myModel);
}

if(component==editButton){
cardLayout.show(editSavePanel,"editSaveButton");
addButton.setEnabled(false);
editButton.setEnabled(false);
undoButton.setEnabled(true);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(false);
searchField.setEnabled(false);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);
employeesTable.setEnabled(false);
cardLayout.show(namePanel,"nameField");
cardLayout.show(salaryPanel,"salaryField");
cardLayout.show(genderPanel,"genderComboBox");
cardLayout.show(panNumberPanel,"panNumberField");
nameField.setText(vNameLabel.getText());
panNumberField.setText(vPanNumberLabel.getText());
salaryField.setText(vSalaryLabel.getText());
genderComboBox.setSelectedItem(vGenderLabel.getText());
}
if(component==editSaveButton){
EmployeeInterface emp=new Employee();
try{
emp.setCode(Integer.parseInt(vCodeLabel.getText()));
emp.setName(nameField.getText());
emp.setSalary(new BigDecimal(salaryField.getText()));
if(genderComboBox.getSelectedItem().equals("Male"))
 emp.setGender("Male");
else
 emp.setGender("Female");
emp.setPANNumber(panNumberField.getText());
new EmployeeManager().updateEmployee(emp);
JOptionPane.showMessageDialog(this,"Employee record updated");
}catch(Exception ee){
ee.printStackTrace();
JOptionPane.showMessageDialog(this,"Employee record not updated");
}
addButton.setEnabled(true);
editButton.setEnabled(false);
undoButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(true);
searchField.setEnabled(true);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);
employeesTable.setEnabled(true);
vCodeLabel.setText("");
vNameLabel.setText("");
vGenderLabel.setText("");
vPanNumberLabel.setText("");
vSalaryLabel.setText("");
nameField.setText("");
salaryField.setText("");
panNumberField.setText("");
cardLayout.show(editSavePanel,"editButton");
cardLayout.show(namePanel,"vNameLabel");
cardLayout.show(salaryPanel,"vSalaryLabel");
cardLayout.show(genderPanel,"vGenderLabel");
cardLayout.show(panNumberPanel,"vPanNumberLabel");
myModel=new MyModel();
employeesTable.setModel(myModel);
}
}
public void mouseClicked(MouseEvent e){
JComponent component=(JComponent)e.getSource();
EmployeeManager empManager=new EmployeeManager();
if(component==searchField&&component.isEnabled()){
addButton.setEnabled(false);
undoButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(true);
searchCancelButton.setEnabled(true);
searchButton.setEnabled(true);
}
if(component==employeesTable&&component.isEnabled()){
JTable tmpTable=(JTable)component;
int row=tmpTable.getSelectedRow();
try{
EmployeeInterface employee=empManager.getEmployee((Integer)myModel.getValueAt(row,2));
vCodeLabel.setText(String.valueOf(employee.getCode()));
vNameLabel.setText(employee.getName());
vPanNumberLabel.setText(employee.getPANNumber());
vSalaryLabel.setText(employee.getSalary().toPlainString());
vGenderLabel.setText(employee.getGender());
addButton.setEnabled(false);
undoButton.setEnabled(true);
editButton.setEnabled(true);
deleteButton.setEnabled(true);
saveToPDFButton.setEnabled(true);
searchField.setEnabled(false);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);
}catch(ProcessException pe){}
}
}
public void mouseEntered(MouseEvent e){
JComponent component=(JComponent)e.getSource();
if(component==searchField&&component.isEnabled()){
addButton.setEnabled(false);
undoButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(true);
searchCancelButton.setEnabled(true);
searchButton.setEnabled(true);
}
}
public void mousePressed(MouseEvent e){}
public void mouseReleased(MouseEvent e){}
public void mouseExited(MouseEvent e){
JComponent component=(JComponent)e.getSource();
if(component==searchField&&component.isEnabled()){
JTextField tmpField=(JTextField)component;
int lengthOfField=tmpField.getText().trim().length();
if(lengthOfField==0){
addButton.setEnabled(true);
undoButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
saveToPDFButton.setEnabled(true);
searchCancelButton.setEnabled(false);
searchButton.setEnabled(false);
}
else{
searchCancelButton.setEnabled(true);
searchButton.setEnabled(true);
}
}
}
}