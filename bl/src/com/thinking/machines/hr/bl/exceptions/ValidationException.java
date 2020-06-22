package com.thinking.machines.hr.bl.exceptions;
import java.util.*;
import java.util.stream.*;
public class ValidationException extends Exception
{
private Map<String,String> exceptionsMap;
public ValidationException(){
exceptionsMap=new HashMap<>();
}
public ValidationException(String property,String exception){
exceptionsMap=new HashMap<>();
addException(property,exception);
}
public void addException(String property,String exception){
exceptionsMap.put(property,exception);
}
public void removeException(String property){
exceptionsMap.remove(property);
}
public void removeAllExceptions(){
exceptionsMap.clear();
}
public List<String> getByProperties(){
return exceptionsMap.keySet().stream().collect(Collectors.toList());
}
public long getSize(){
return exceptionsMap.size();
}
public boolean isEmpty(){
return exceptionsMap.size()==0;
}
public boolean hasException(){
return exceptionsMap.size()>0;
}
public Map<String,String> getMap(){
return new HashMap<String,String>(exceptionsMap);
}
}