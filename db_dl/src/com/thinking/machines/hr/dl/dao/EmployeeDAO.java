package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import java.math.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class EmployeeDAO implements EmployeeDAOInterface
{
private static final String fileName="employee.emp";
private Connection connection;
private ResultSet resultSet;
private PreparedStatement preparedStatement;
public void communicateDatabase() throws Exception{
Class.forName("com.mysql.cj.jdbc.Driver");
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeesDB","guptaShivam","gupta");
}
public void haltCommunication() throws Exception{
preparedStatement.close();
connection.close();
}
public void add(EmployeeDTOInterface employee)throws DAOException
{
try{
communicateDatabase();
preparedStatement=connection.prepareStatement("insert into Employees(name,gender,salary,panNumber) values(?,?,?,?)");
preparedStatement.setString(1,employee.getName());
preparedStatement.setString(2,employee.getGender());
preparedStatement.setInt(3,employee.getSalary().intValue());
preparedStatement.setString(4,employee.getPANNumber());
preparedStatement.executeUpdate();
haltCommunication();
}catch(Exception exception){
throw new DAOException(exception.getMessage());
}
}
public void update(EmployeeDTOInterface employee)throws DAOException
{
try{
communicateDatabase();
preparedStatement=connection.prepareStatement("update Employees set name=?,gender=?,salary=?,panNumber=? where code=?");
preparedStatement.setString(1,employee.getName());
preparedStatement.setString(2,employee.getGender());
preparedStatement.setInt(3,employee.getSalary().intValue());
preparedStatement.setString(4,employee.getPANNumber());
preparedStatement.setInt(5,employee.getCode());
preparedStatement.executeUpdate();
haltCommunication();
}catch(Exception exception){
throw new DAOException(exception.getMessage());
}
}
public void delete(int code)throws DAOException
{
try{
communicateDatabase();
preparedStatement=connection.prepareStatement("delete from Employees where code=?");
preparedStatement.setInt(1,code);
preparedStatement.executeUpdate();
haltCommunication();
}catch(Exception exception){
throw new DAOException(exception.getMessage());
}
}
public EmployeeDTOInterface getByCode(int code)throws DAOException
{
try{
communicateDatabase();
preparedStatement=connection.prepareStatement("select * from Employees where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
resultSet.next();
EmployeeDTO employee=new EmployeeDTO();
employee.setCode(resultSet.getInt("code"));
employee.setName(resultSet.getString("name").trim());
employee.setGender(resultSet.getString("gender").trim());
employee.setSalary(BigDecimal.valueOf(resultSet.getInt("salary")));
employee.setPANNumber(resultSet.getString("panNumber").trim());
resultSet.close();
haltCommunication();
return employee;
}catch(Exception exception){
throw new DAOException("No record found");
}
}
public EmployeeDTOInterface getByPANNumber(String panNumber)throws DAOException
{
try{
communicateDatabase();
preparedStatement=connection.prepareStatement("select * from Employees where panNumber=?");
preparedStatement.setString(1,panNumber);
resultSet=preparedStatement.executeQuery();
resultSet.next();
EmployeeDTO employee=new EmployeeDTO();
employee.setCode(resultSet.getInt("code"));
employee.setName(resultSet.getString("name").trim());
employee.setGender(resultSet.getString("gender").trim());
employee.setSalary(BigDecimal.valueOf(resultSet.getInt("salary")));
employee.setPANNumber(resultSet.getString("panNumber").trim());
resultSet.close();
haltCommunication();
return employee;
}catch(Exception exception){
throw new DAOException("No record found");
}
}
public List<EmployeeDTOInterface> getAll()throws DAOException
{
try{
List<EmployeeDTOInterface>employeeList=new LinkedList<EmployeeDTOInterface>();
EmployeeDTO employee;
communicateDatabase();
preparedStatement=connection.prepareStatement("select * from Employees order by code");
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
employee=new EmployeeDTO();
employee.setCode(resultSet.getInt("code"));
employee.setName(resultSet.getString("name").trim());
employee.setGender(resultSet.getString("gender").trim());
employee.setSalary(BigDecimal.valueOf(resultSet.getInt("salary")));
employee.setPANNumber(resultSet.getString("panNumber").trim());
employeeList.add(employee);
}
resultSet.close();
haltCommunication();
return employeeList;
}catch(Exception exception){
exception.printStackTrace();
throw new DAOException(exception.getMessage());
}
}
public long getCount()throws DAOException
{
long recordCount=0;
try{
communicateDatabase();
preparedStatement=connection.prepareStatement("select count(*) as rowcount from Employees");
resultSet=preparedStatement.executeQuery();
resultSet.next();
recordCount=resultSet.getInt("rowcount");
resultSet.close();
haltCommunication();
return recordCount;
}catch(Exception exception){
throw new DAOException(exception.getMessage());
}
}
}