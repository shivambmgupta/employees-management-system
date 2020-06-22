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
try{
EmployeeDAO employeeDAO=new EmployeeDAO();
employeeDAO.delete(code);
System.out.printf("Employee with code : %d deleted\n",code);
}catch(DAOException dAOException)
{
System.out.println(dAOException.getMessage());
}
}
}