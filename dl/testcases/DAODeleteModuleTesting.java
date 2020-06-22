import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import java.math.*;
import java.io.*;
public class DAODeleteModuleTesting
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
String name=gg[1];
String gender=gg[2];
String panNumber=gg[3];
BigDecimal salary=new BigDecimal(gg[4]);
EmployeeDTO employee=new EmployeeDTO();
employee.setCode(code);
employee.setName(name);
employee.setGender(gender);
employee.setPANNumber(panNumber);
employee.setSalary(salary);
EmployeeDAO employeeDAO=new EmployeeDAO();
try{
employeeDAO.delete(employee);
System.out.printf("Employee with code : %d deleted\n",employee.getCode());
}catch(DAOException dAOException)
{
System.out.println(dAOException.getMessage());
}
}
}