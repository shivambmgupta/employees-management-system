import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import java.math.*;
import java.io.*;
public class DAOCountTesting
{
public static void main(String gg[])
{
EmployeeDAO employeeDAO=new EmployeeDAO();
try{
System.out.println("Total number of records are : "+employeeDAO.getCount());
}catch(DAOException dAOException)
{
System.out.println(dAOException.getMessage());
}
}
}