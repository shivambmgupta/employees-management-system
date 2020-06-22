import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import java.math.*;
import java.io.*;
public class DAOGetByPANTesting
{
public static void main(String gg[])
{
String panNumber=gg[0];
EmployeeDAO employeeDAO=new EmployeeDAO();
try{
EmployeeDTOInterface employee;
employee=employeeDAO.getByPANNumber(panNumber);
System.out.println("Details Found");
System.out.printf("Code : %d\nName : %s\nGender : %s\nPan number : %s\nSalary : %s\n",employee.getCode(),employee.getName(),employee.getGender(),employee.getPANNumber(),employee.getSalary().toPlainString());
}catch(DAOException dAOException)
{
System.out.println(dAOException.getMessage());
}
}
}