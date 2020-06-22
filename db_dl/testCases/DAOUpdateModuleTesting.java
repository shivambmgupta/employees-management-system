import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import java.math.*;
import java.io.*;
public class DAOUpdateModuleTesting
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
employeeDAO.update(employee);
System.out.printf("Updated details are  : \nName : %s\nGender : %s\nCode : %d\nPan Number : %s\nSalary : %s\n\n",employee.getName(),employee.getGender(),employee.getCode(),employee.getPANNumber(),employee.getSalary().toPlainString());
}catch(DAOException dAOException)
{
System.out.println(dAOException.getMessage());
}
}
}