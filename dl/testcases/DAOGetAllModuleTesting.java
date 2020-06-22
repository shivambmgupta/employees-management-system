import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import java.math.*;
import java.io.*;
import java.util.*;
public class DAOGetAllModuleTesting
{
public static void main(String gg[])
{
EmployeeDAO employeeDAO=new EmployeeDAO();
try{
List<EmployeeDTOInterface> employeeList=new ArrayList<EmployeeDTOInterface>();
EmployeeDTOInterface it=new EmployeeDTO();
employeeList=employeeDAO.getAll();
for(ListIterator<EmployeeDTOInterface> employee=employeeList.listIterator();employee.hasNext();)
{
it=employee.next();
System.out.println("Details are : ");
System.out.printf("Code : %d\nName : %s\nGender : %s\nPan number : %s\nSalary : %s\n\n",it.getCode(),it.getName(),it.getGender(),it.getPANNumber(),it.getSalary().toPlainString());
}
}catch(DAOException dAOException)
{
System.out.println(dAOException.getMessage());
}
}
}