package com.thinking.machines.hr.bl.manager;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import java.util.stream.*;
import java.math.*;
import com.thinking.machines.hr.tmutils.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
public class EmployeeManager implements EmployeeManagerInterface
{
private Map<Integer,EmployeeInterface> codeWiseEmployeesMap;
private Map<String,EmployeeInterface> panNumberWiseEmployeesMap;
private List<EmployeeInterface> codeWiseEmployeesList;
private List<EmployeeInterface> nameWiseEmployeesList;
public EmployeeManager()
{
populateDataStructures();
}

private void populateDataStructures()
{
codeWiseEmployeesMap=new TreeMap<>();
panNumberWiseEmployeesMap=new TreeMap<>();
codeWiseEmployeesList=new LinkedList<>();
nameWiseEmployeesList=new LinkedList<>();
try{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
List<EmployeeDTOInterface> employeeList=employeeDAO.getAll();
employeeList.forEach((e)->{
EmployeeInterface employee=new Employee();
employee.setCode(e.getCode());
employee.setName(e.getName());
employee.setGender(e.getGender());
employee.setSalary(e.getSalary());
employee.setPANNumber(e.getPANNumber());
codeWiseEmployeesMap.put(employee.getCode(),employee);
panNumberWiseEmployeesMap.put(employee.getPANNumber().toUpperCase(),employee);
codeWiseEmployeesList.add(employee);
nameWiseEmployeesList.add(employee);
});
nameWiseEmployeesList.sort(Comparator.comparing((EmployeeInterface e)->{return e.getName().toUpperCase();}).thenComparing(EmployeeInterface::getCode));
}catch(DAOException daoException){
return;
}
}

public void addEmployee(EmployeeInterface employee) throws ValidationException,ProcessException
{
ValidationException validationException=new ValidationException();
if(employee==null){
validationException.addException("Employee","Required");
throw validationException;
}
if(employee.getCode()!=0)
 validationException.addException("Code","Should be zero");
if(employee.getCode()<0)
 validationException.addException("Code","Can't be negative");
if(employee.getName()==null ||employee.getName().trim().length()==0)
 validationException.addException("Name","Required");
if(employee.getName()!=null && employee.getName().trim().length()>30)
 validationException.addException("Name","Exceeds the limit(30 Characters)");
if(employee.getGender()==null || employee.getGender().trim().length()==0)
 validationException.addException("Gender","Required");
if(employee.getGender()!=null &&!(employee.getGender().equalsIgnoreCase("Male")||employee.getGender().equalsIgnoreCase("Female")))
 validationException.addException("Gender","Invalid");
if(employee.getSalary()==null)
 validationException.addException("Salary","Requried");
else{
try{
if(employee.getSalary().signum()==-1)
validationException.addException("Salary","Invalid");
}catch(NumberFormatException numberFormatException){
validationException.addException("Salary","Invalid");
}
}
if(employee.getPANNumber()==null || employee.getPANNumber().trim().length()==0)
 validationException.addException("Pan Number","Required");
if(employee.getPANNumber()!=null && employee.getPANNumber().trim().length()>15)
 validationException.addException("Pan Number","Exceeds limit(15 characters)");
if(employee.getPANNumber()!=null && panNumberWiseEmployeesMap.containsKey(employee.getPANNumber().toUpperCase()))
 validationException.addException("Pan Number","Exists");

if(validationException.hasException()) throw validationException;
try{
EmployeeDTOInterface dlEmployee=new EmployeeDTO();
POJOCopier.copy(dlEmployee,employee);
EmployeeDAOInterface daoEmployee=new EmployeeDAO();
daoEmployee.add(dlEmployee);
employee.setCode(dlEmployee.getCode());
EmployeeInterface dsEmployee=new Employee();
POJOCopier.copy(dsEmployee,employee);
codeWiseEmployeesMap.put(dsEmployee.getCode(),dsEmployee);
panNumberWiseEmployeesMap.put(dsEmployee.getPANNumber().toUpperCase(),dsEmployee);
codeWiseEmployeesList.add(dsEmployee);
int index=Collections.binarySearch(nameWiseEmployeesList,dsEmployee,Comparator.comparing(EmployeeInterface::getName).thenComparing(EmployeeInterface::getCode));
if(index<0)index=(index+1)*(-1);
nameWiseEmployeesList.add(index,dsEmployee); 
}catch(DAOException daoException){
throw new ProcessException(daoException.getMessage());
}
}//end of add.

public void deleteEmployee(int code) throws ValidationException,ProcessException
{
ValidationException validationException=new ValidationException();
if(code<=0){
validationException.addException("Code","Invalid");
throw validationException;
}
try{
new EmployeeDAO().delete(code);
EmployeeInterface employee=codeWiseEmployeesMap.get(code);
codeWiseEmployeesMap.remove(employee.getCode());
panNumberWiseEmployeesMap.remove(employee.getPANNumber().toUpperCase());
int index=Collections.binarySearch(codeWiseEmployeesList,employee,Comparator.comparing(EmployeeInterface::getCode));
codeWiseEmployeesList.remove(index);
index=Collections.binarySearch(nameWiseEmployeesList,employee,Comparator.comparing(EmployeeInterface::getName).thenComparing(EmployeeInterface::getCode));
nameWiseEmployeesList.remove(index);
}catch(DAOException daoException){
throw new ProcessException(daoException.getMessage());
}
}

public void updateEmployee(EmployeeInterface employee) throws ValidationException,ProcessException
{
ValidationException validationException=new ValidationException();
if(employee==null){
validationException.addException("Employee","Required");
throw validationException;
}
if(employee.getCode()==0)
 validationException.addException("Code","Should not be zero");
if(employee.getCode()<0)
 validationException.addException("Code","Can't be negative");
if(!codeWiseEmployeesMap.containsKey(employee.getCode()))
 validationException.addException("Code","Does not exist");
if(employee.getName()==null ||employee.getName().trim().length()==0)
 validationException.addException("Name","Required");
if(employee.getName()!=null && employee.getName().trim().length()>30)
 validationException.addException("Name","Exceeds the limit(30 Characters)");
if(employee.getGender()==null || employee.getGender().trim().length()==0)
 validationException.addException("Gender","Required");
if(employee.getGender()!=null &&!(employee.getGender().equalsIgnoreCase("Male")||employee.getGender().equalsIgnoreCase("Female")))
 validationException.addException("Gender","Invalid");
if(employee.getSalary()==null)
 validationException.addException("Salary","Requried");
else{
try{
if(employee.getSalary().signum()==-1)
validationException.addException("Salary","Invalid");
}catch(NumberFormatException numberFormatException){
validationException.addException("Salary","Invalid");
}
}
if(employee.getPANNumber()==null || employee.getPANNumber().trim().length()==0)
 validationException.addException("Pan Number","Required");
if(employee.getPANNumber()!=null && employee.getPANNumber().trim().length()>15)
 validationException.addException("Pan Number","Exceeds limit(15 characters)");

if(validationException.hasException()) throw validationException;

try{
EmployeeDTOInterface dlEmployee=new EmployeeDTO();
POJOCopier.copy(dlEmployee,employee);
EmployeeDAOInterface daoEmployee=new EmployeeDAO();
daoEmployee.update(dlEmployee);
EmployeeInterface dsEmployee=new Employee();
POJOCopier.copy(dsEmployee,employee);
codeWiseEmployeesMap.remove(employee.getCode());
codeWiseEmployeesMap.put(dsEmployee.getCode(),dsEmployee);
panNumberWiseEmployeesMap.remove(employee.getPANNumber());
panNumberWiseEmployeesMap.put(dsEmployee.getPANNumber().toUpperCase(),dsEmployee);
int index=Collections.binarySearch(codeWiseEmployeesList,employee,Comparator.comparing(EmployeeInterface::getCode));
codeWiseEmployeesList.set(index,dsEmployee);
nameWiseEmployeesList.remove(employee);
index=Collections.binarySearch(nameWiseEmployeesList,dsEmployee,Comparator.comparing(EmployeeInterface::getName).thenComparing(EmployeeInterface::getCode));
if(index<0)index=(index+1)*(-1);
nameWiseEmployeesList.add(index,dsEmployee); 
}catch(DAOException daoException){
throw new ProcessException(daoException.getMessage());
}
}
public boolean hasEmployees(){
return codeWiseEmployeesList.size()>0;
}
public int getNumberOfEmployees(){
return codeWiseEmployeesList.size();
}

public List<EmployeeInterface> getAll(OrderBy orderBy)
{
if(orderBy==OrderBy.CODE){
List<EmployeeInterface> newCodeWiseEmployeesList=new LinkedList<>();
Employee dsEmployee;
for(EmployeeInterface employee:codeWiseEmployeesList){
dsEmployee=new Employee();
POJOCopier.copy(dsEmployee,employee);
newCodeWiseEmployeesList.add(dsEmployee);
}
return newCodeWiseEmployeesList;
}
if(orderBy==OrderBy.NAME){
List<EmployeeInterface> newNameWiseEmployeesList=new LinkedList<>();
Employee dsEmployee;
for(EmployeeInterface employee:nameWiseEmployeesList){
dsEmployee=new Employee();
POJOCopier.copy(dsEmployee,employee);
newNameWiseEmployeesList.add(dsEmployee);
}
return newNameWiseEmployeesList;
}
if(orderBy==OrderBy.PAN_NUMBER){
List<EmployeeInterface> panNumberWiseEmployeesList=new LinkedList<>();
EmployeeInterface dsEmployee;
for(EmployeeInterface employee:codeWiseEmployeesList)
{
dsEmployee=new Employee();
POJOCopier.copy(dsEmployee,employee);
panNumberWiseEmployeesList.add(dsEmployee);
}
panNumberWiseEmployeesList.sort(Comparator.comparing(EmployeeInterface::getPANNumber));
return panNumberWiseEmployeesList;
}
if(orderBy==OrderBy.GENDER){
List<EmployeeInterface> genderWiseEmployeesList=new LinkedList<>();
EmployeeInterface dsEmployee;
for(EmployeeInterface employee:codeWiseEmployeesList)
{
dsEmployee=new Employee();
POJOCopier.copy(dsEmployee,employee);
genderWiseEmployeesList.add(dsEmployee);
}
genderWiseEmployeesList.sort(Comparator.comparing(EmployeeInterface::getGender));
return genderWiseEmployeesList;
}
if(orderBy==OrderBy.SALARY)
{
List<EmployeeInterface> salaryWiseEmployeesList=new LinkedList<>();
EmployeeInterface dsEmployee;
for(EmployeeInterface employee:codeWiseEmployeesList)
{
dsEmployee=new Employee();
POJOCopier.copy(dsEmployee,employee);
salaryWiseEmployeesList.add(dsEmployee);
}
salaryWiseEmployeesList.sort(Comparator.comparing(EmployeeInterface::getSalary));
return salaryWiseEmployeesList;
}
return null;
}
public void deleteAll() throws ProcessException{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
List<EmployeeInterface> cannotBeDeletedList=new LinkedList<>();
codeWiseEmployeesList.parallelStream().forEach((e)->{
try{
employeeDAO.delete(e.getCode());
codeWiseEmployeesMap.remove(e.getCode());
panNumberWiseEmployeesMap.remove(e.getPANNumber().toUpperCase());
nameWiseEmployeesList.remove(e);
}catch(DAOException daoException){
cannotBeDeletedList.add(e);
}
});
codeWiseEmployeesList.clear();
codeWiseEmployeesList=cannotBeDeletedList;
}
public EmployeeInterface getEmployee(int code)throws ProcessException{
EmployeeInterface dsEmployee=codeWiseEmployeesMap.get(code);
if(dsEmployee==null)
 throw new ProcessException("No record found");
EmployeeInterface employee=new Employee();
POJOCopier.copy(employee,dsEmployee);
return employee;
}
}