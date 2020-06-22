import java.util.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.manager.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.math.*;
import java.util.stream.*;
import java.io.*;
public class test1
{
public static void main(String gg[])
{
int choice=0;
int choice1=0;
int vCode=0;
String vName="";
String vGender="";
String vPANNumber="";
BigDecimal vSalary=null;
try{
InputStreamReader isr=new InputStreamReader(System.in);
BufferedReader br=new BufferedReader(isr);
EmployeeManager empManager=new EmployeeManager();
while(true){
System.out.println("Menu : ");
System.out.println("1 TO ADD");
System.out.println("2 TO UPDATE");
System.out.println("3 TO DELETE");
System.out.println("4 TO GET NUMBER OF EMPLOYEES");
System.out.println("5 TO CHECK IS EMPTY");
System.out.println("6 TO GET ALL");
System.out.println("7 TO DELETE ALL");
System.out.println("8 TO EXIT");
System.out.print("Enter your choice : ");
choice=Integer.parseInt(br.readLine());
if(choice==1){
Employee employee=new Employee();
System.out.print("Enter code : ");
vCode=Integer.parseInt(br.readLine());
System.out.print("Enter Name : ");
vName=br.readLine();
System.out.print("Enter Gender : ");
vGender=br.readLine();
System.out.print("Enter Salary : ");
vSalary=new BigDecimal(br.readLine());
System.out.print("Enter Pan Number : ");
vPANNumber=br.readLine();
employee.setCode(vCode);
employee.setName(vName);
employee.setGender(vGender);
employee.setSalary(vSalary);
employee.setPANNumber(vPANNumber);
empManager.addEmployee(employee);
System.out.println("Employee added.");
}
else if(choice==2){
Employee employee=new Employee();
System.out.print("Enter code of employee you want to update : ");
vCode=Integer.parseInt(br.readLine());
System.out.print("Enter Name : ");
vName=br.readLine();
System.out.print("Enter Gender : ");
vGender=br.readLine();
System.out.print("Enter Salary : ");
vSalary=new BigDecimal(br.readLine());
System.out.print("Enter Pan Number : ");
vPANNumber=br.readLine();
employee.setCode(vCode);
employee.setName(vName);
employee.setGender(vGender);
employee.setSalary(vSalary);
employee.setPANNumber(vPANNumber);
empManager.updateEmployee(employee);
System.out.println("Employee updated.");
}
else if(choice==3){
System.out.print("Enter code of employee you want to update : ");
vCode=Integer.parseInt(br.readLine());
empManager.deleteEmployee(vCode);
System.out.println("Employee deleted.");
}
else if(choice==4)
 System.out.println("Total number of employee(s) is/are : "+empManager.getNumberOfEmployees());
else if(choice==5)
 System.out.println("HAS EMPLOYEES? : "+empManager.hasEmployees());
else if(choice==6){
EmployeeManager.OrderBy orderBy=EmployeeManager.CODE;
System.out.printf("  Sub-Menu : \n1(DEFAULT) CODE\n2 NAME\n3 PAN_NUMBER\n4 GENDER\n5 SALARY\n6 Exit.\n");
System.out.print("Enter your  choice : ");
choice1=Integer.parseInt(br.readLine());
if(choice1==1) orderBy=EmployeeManager.CODE;
if(choice1==2) orderBy=EmployeeManager.NAME;
if(choice1==3) orderBy=EmployeeManager.PAN_NUMBER;
if(choice1==4) orderBy=EmployeeManager.GENDER;
if(choice1==5) orderBy=EmployeeManager.SALARY; 
empManager.getAll(orderBy).forEach((e)->System.out.println(e));
}
else if(choice==7){
System.out.print("CONFIRM? (strict yes/no(default)) : ");
if(br.readLine().equalsIgnoreCase("yes"))
 empManager.deleteAll();
}
else if(choice==8) break;
else continue;
}
}catch(ValidationException ve){
System.out.println(ve.getMap());
}
catch(ProcessException pe){
pe.printStackTrace();
}
catch(Exception e){
e.printStackTrace();
}
}
}