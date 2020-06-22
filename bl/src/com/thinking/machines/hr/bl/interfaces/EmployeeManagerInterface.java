package com.thinking.machines.hr.bl.interfaces;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
public interface EmployeeManagerInterface
{
public enum OrderBy{CODE,NAME,PAN_NUMBER,GENDER,SALARY};
public static final OrderBy CODE=OrderBy.CODE;
public static final OrderBy NAME=OrderBy.NAME;
public static final OrderBy PAN_NUMBER=OrderBy.PAN_NUMBER;
public static final OrderBy GENDER=OrderBy.GENDER;
public static final OrderBy SALARY=OrderBy.SALARY;

public void addEmployee(EmployeeInterface employeeInterface) throws ValidationException,ProcessException;
public void deleteEmployee(int code) throws ValidationException,ProcessException;
public void updateEmployee(EmployeeInterface employee) throws ValidationException,ProcessException;
public boolean hasEmployees();
public int getNumberOfEmployees();
public List<EmployeeInterface> getAll(OrderBy orderBy);
public void deleteAll() throws ProcessException;
}