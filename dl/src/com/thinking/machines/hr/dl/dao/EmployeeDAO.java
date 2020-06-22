package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import java.math.*;
import java.util.*;
import java.io.*;
public class EmployeeDAO implements EmployeeDAOInterface
{
private static final String fileName="employee.emp";
public void add(EmployeeDTOInterface employee)throws DAOException
{
int lastGeneratedCode=0,numberOfRecordsAdded=0;
String header="";
try{
File file=new File(fileName);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()>0)
{
header=randomAccessFile.readLine();
lastGeneratedCode=Integer.parseInt(header.substring(0,10).trim());
numberOfRecordsAdded=Integer.parseInt(header.substring(10).trim());
String vPANNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
randomAccessFile.readLine();
if(vPANNumber.equalsIgnoreCase(employee.getPANNumber()))
{
randomAccessFile.close();
throw new DAOException("PAN Number : "+employee.getPANNumber()+" already exist");
}
}
}
else
{
header=String.format("%10s%10s",lastGeneratedCode,numberOfRecordsAdded);
randomAccessFile.writeBytes(header+"\n");
}
lastGeneratedCode++;
numberOfRecordsAdded++;
int newCode=lastGeneratedCode;
randomAccessFile.writeBytes(newCode+"\n");
randomAccessFile.writeBytes(employee.getName()+"\n");
randomAccessFile.writeBytes(employee.getGender()+"\n");
randomAccessFile.writeBytes(employee.getPANNumber()+"\n");
randomAccessFile.writeBytes(employee.getSalary().toPlainString()+"\n");
randomAccessFile.seek(0);
header=String.format("%10s%10s",lastGeneratedCode,numberOfRecordsAdded);
randomAccessFile.writeBytes(header);
randomAccessFile.close();
employee.setCode(newCode);
}catch(FileNotFoundException fnfe){throw new DAOException("Error");}
catch(IOException iOException){throw new DAOException("Error");}
}
public void update(EmployeeDTOInterface employee)throws DAOException
{
boolean recordFound=false;
int vCode=0;
String vName="";
String vGender="";
String vPANNumber="";
boolean found=false;
int panNumberFoundAgainstCode=0;
BigDecimal vSalary=new BigDecimal("0.00");
try{
File file=new File(fileName);
if(!file.exists()) throw new DAOException("No record found");
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0){
randomAccessFile.close();
throw new DAOException("No record found");
}
randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length()){
randomAccessFile.close();
throw new DAOException("No record found");
}
while(randomAccessFile.getFilePointer()<randomAccessFile.length()){
vCode=Integer.parseInt(randomAccessFile.readLine());
randomAccessFile.readLine();
randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
randomAccessFile.readLine();
if(!found && vCode==employee.getCode()) found=true;
if(panNumberFoundAgainstCode!=0 && vPANNumber.equalsIgnoreCase(employee.getPANNumber()))
 panNumberFoundAgainstCode=vCode;
}//end of while
if(!found){
randomAccessFile.close();
throw new DAOException("No record found");
}
if(panNumberFoundAgainstCode!=vCode && panNumberFoundAgainstCode!=0){
randomAccessFile.close();
throw new DAOException("New provided PAN Number already exists");
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) file.delete();
tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length()){
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
vSalary=new BigDecimal(randomAccessFile.readLine());
if(vCode!=employee.getCode())
{
tmpRandomAccessFile.writeBytes(vCode+"\n");
tmpRandomAccessFile.writeBytes(vName+"\n");
tmpRandomAccessFile.writeBytes(vGender+"\n");
tmpRandomAccessFile.writeBytes(vPANNumber+"\n");
tmpRandomAccessFile.writeBytes(vSalary.toPlainString()+"\n");
}
else
{
tmpRandomAccessFile.writeBytes(employee.getCode()+"\n");
tmpRandomAccessFile.writeBytes(employee.getName()+"\n");
tmpRandomAccessFile.writeBytes(employee.getGender()+"\n");
tmpRandomAccessFile.writeBytes(employee.getPANNumber()+"\n");
tmpRandomAccessFile.writeBytes(employee.getSalary().toPlainString()+"\n");
}
}//end of while
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
 randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
randomAccessFile.setLength(tmpRandomAccessFile.length());
randomAccessFile.close();
tmpRandomAccessFile.close();
tmpFile.delete();
}catch(FileNotFoundException fnfe){throw new DAOException("Error");}
catch(IOException iOException){throw new DAOException("Error");}
}
public void delete(int code)throws DAOException
{
boolean recordFound=false;
int vCode=0;
String vName="";
String vGender="";
String vPANNumber="";
BigDecimal vSalary=new BigDecimal("0.00");
String header="";
int numberOfRecords=0;
int lastCodeGenerated=0;
try{
File file=new File(fileName);
if(file.exists()==false)throw new DAOException("No Record Found.");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No Record Found.");
}
randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("No Record Found.");
}
randomAccessFile.seek(0);
File temp=new File("temp.tmp");
if(file.exists())file.delete();
temp=new File("temp.tmp");
RandomAccessFile tempRAF=new RandomAccessFile(temp,"rw");
header=randomAccessFile.readLine();
lastCodeGenerated=Integer.parseInt(header.substring(0,10).trim());
numberOfRecords=Integer.parseInt(header.substring(10).trim());
tempRAF.writeBytes(header+"\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
vSalary=new BigDecimal(randomAccessFile.readLine());
if(vCode!=code)
{
tempRAF.writeBytes(vCode+"\n");
tempRAF.writeBytes(vName+"\n");
tempRAF.writeBytes(vGender+"\n");
tempRAF.writeBytes(vPANNumber+"\n");
tempRAF.writeBytes(vSalary.toPlainString()+"\n");
}
else
{
recordFound=true;
numberOfRecords--;
}
}
if(recordFound)
{
tempRAF.seek(0);
header=String.format("%10s%10s",lastCodeGenerated,numberOfRecords);
tempRAF.writeBytes(header);
tempRAF.seek(0);
randomAccessFile.seek(0);
while(tempRAF.getFilePointer()<tempRAF.length())
 randomAccessFile.writeBytes(tempRAF.readLine()+"\n");
randomAccessFile.setLength(tempRAF.length());
}
tempRAF.close();
temp.delete();
randomAccessFile.close();
if(!recordFound)throw new DAOException("No Record Found.");
}catch(IOException iOException)
{
throw new DAOException("No Record Found.");
}
}
public EmployeeDTOInterface getByCode(int code)throws DAOException
{
int vCode=0;
String vName="";
String vGender="";
String vPANNumber="";
BigDecimal vSalary=new BigDecimal("0.00");
try{
File file=new File(fileName);
if(file.exists()==false) throw new DAOException("No Record Found.");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(fileName,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No Record Found.");
}
randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("No Record Found.");
}
EmployeeDTOInterface employee=new EmployeeDTO();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
vSalary=new BigDecimal(randomAccessFile.readLine());
if(vCode==code)
{
employee.setCode(vCode);
employee.setName(vName);
employee.setGender(vGender);
employee.setPANNumber(vPANNumber);
employee.setSalary(vSalary);
randomAccessFile.close();
return employee;
}
}
randomAccessFile.close();
throw new DAOException("No Record Found.");
}catch(IOException iOException)
{
throw new DAOException("No Record Found.");
}
}
public EmployeeDTOInterface getByPANNumber(String panNumber)throws DAOException
{
int vCode=0;
String vName="";
String vGender="";
String vPANNumber="";
BigDecimal vSalary=new BigDecimal("0.00");
try{
File file=new File(fileName);
if(file.exists()==false) throw new DAOException("No Record Found.");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(fileName,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No Record Found.");
}
randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("No Record Found.");
}
EmployeeDTOInterface employee=new EmployeeDTO();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
vSalary=new BigDecimal(randomAccessFile.readLine());
if(vPANNumber.equalsIgnoreCase(panNumber))
{
employee.setCode(vCode);
employee.setName(vName);
employee.setGender(vGender);
employee.setPANNumber(vPANNumber);
employee.setSalary(vSalary);
randomAccessFile.close();
return employee;
}
}
randomAccessFile.close();
throw new DAOException("No Record Found.");
}catch(IOException iOException)
{
throw new DAOException("No Record Found.");
}
}
public List<EmployeeDTOInterface> getAll()throws DAOException
{
int vCode=0;
String vName="";
String vGender="";
String vPANNumber="";
List<EmployeeDTOInterface>employeeList=new LinkedList<EmployeeDTOInterface>();
BigDecimal vSalary=new BigDecimal("0.00");
try{
File file=new File(fileName);
if(file.exists()==false)throw new DAOException("No Record Found.");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No Record Found.");
}
randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("No Record Found.");
}
EmployeeDTOInterface employee;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
vSalary=new BigDecimal(randomAccessFile.readLine());
employee=new EmployeeDTO();
employee.setCode(vCode);
employee.setName(vName);
employee.setGender(vGender);
employee.setPANNumber(vPANNumber);
employee.setSalary(vSalary);
employeeList.add(employee);
}
randomAccessFile.close();
return employeeList;
}catch(IOException iOException)
{
throw new DAOException("No Record Found.");
}
}
public long getCount()throws DAOException
{
try{
File file=new File(fileName);
if(file.exists()==false)throw new DAOException("Zero Records");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(fileName,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Zero Records");
}
Long numberOfRecords=Long.parseLong(randomAccessFile.readLine().substring(10).trim());
randomAccessFile.close();
return numberOfRecords;
}catch(IOException iOException)
{
throw new DAOException("Zero Records.");
}
}
}