package com.thinking.machines.hr.bl.pojo;
import com.thinking.machines.hr.bl.interfaces.*;
import java.math.*;
import java.io.*;
public class Employee implements EmployeeInterface
{
private int code;
private String name;
private String gender;
private BigDecimal salary;
private String PANNumber;
public Employee()
{
}
public Employee(int code,String name,String gender,BigDecimal salary,String PANNumber)
{
this.code=code;
this.name=name;
this.gender=gender;
this.salary=salary;
this.PANNumber=PANNumber;
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setGender(String gender)
{
this.gender=gender;
}
public String getGender()
{
return this.gender;
}
public boolean isMale()
{
return this.gender.equalsIgnoreCase("Male");
}
public boolean isFemale()
{
return this.gender.equalsIgnoreCase("Female");
}
public void setSalary(BigDecimal salary)
{
this.salary=salary;
}
public BigDecimal getSalary()
{
return this.salary;
}
public void setPANNumber(String panNumber)
{
this.PANNumber=panNumber;
}
public String getPANNumber()
{
return this.PANNumber;
}
public int hashCode()
{
return this.code;
}
public boolean equals(Object other)
{
if(!(other instanceof EmployeeInterface))return false;
EmployeeInterface employee=(EmployeeInterface)other;
return this.code==employee.getCode();
}
public int compareTo(EmployeeInterface other)
{
return this.code-other.getCode();
}
public String toString(){
return "["+this.name+", "+this.code+", "+this.gender+", "+this.salary.toPlainString()+", "+this.PANNumber+".]";
}
}