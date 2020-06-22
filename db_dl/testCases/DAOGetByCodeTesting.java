import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import java.math.*;
import java.io.*;
public class DAOGetByCodeTesting
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
EmployeeDAO employeeDAO=new EmployeeDAO();
try{
EmployeeDTOInterface employee;
employee=employeeDAO.getByCode(code);
System.out.println("Details Found");
System.out.printf("Code : %d\nName : %s\nGender : %s\nPan number : %s\nSalary : %s\n",employee.getCode(),employee.getName(),employee.getGender(),employee.getPANNumber(),employee.getSalary().toPlainString());
}catch(DAOException dAOException)
{
System.out.println(dAOException.getMessage());
}
}
}