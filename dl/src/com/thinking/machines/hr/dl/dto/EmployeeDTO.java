package com.thinking.machines.hr.dl.dto;
import com.thinking.machines.hr.dl.interfaces.*;
import java.math.*;
import java.io.*;
public class EmployeeDTO implements EmployeeDTOInterface
{
private int code;
private String name;
private String gender;
private BigDecimal salary;
private String PANNumber;
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
if(!(other instanceof EmployeeDTOInterface))return false;
EmployeeDTOInterface employee=(EmployeeDTOInterface)other;
return this.code==employee.getCode();
}
public int compareTo(EmployeeDTOInterface other)
{
return this.code-other.getCode();
}

public String toString()
{
String data="{code:"+code+",name:"+name+",gender:"+gender+",salary:"+salary+",PANNumber:"+PANNumber+"}";
return data;
}
}
}
