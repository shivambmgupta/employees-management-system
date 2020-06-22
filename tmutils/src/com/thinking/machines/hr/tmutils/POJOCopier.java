package com.thinking.machines.hr.tmutils;
import java.util.*;
import java.lang.reflect.*;
public class POJOCopier
{
private POJOCopier(){}
public static void copy(Object targetObject,Object sourceObject)
{
Class<?> sourceClass=sourceObject.getClass();
Class<?> targetClass=targetObject.getClass();
Method[] sourceMethods=sourceClass.getDeclaredMethods();
Method[] targetMethods=targetClass.getDeclaredMethods();
int sourceMethodIndex=0;
for(Method sourceMethod:sourceMethods){
if(isGetter(sourceMethod)){
sourceMethodIndex=getSetterMethodIndex(targetMethods,sourceMethod);
if(sourceMethodIndex==-1) continue;
invoke(targetObject,targetMethods[sourceMethodIndex],sourceObject,sourceMethod);
}
}
}
private static boolean isGetter(Method method)
{
String methodName=method.getName();
if(methodName.length()<4)return false;
if(!methodName.startsWith("get"))return false;
if(!isUpperCaseAlphabet(methodName.charAt(3))) return false;
if(method.getReturnType().toString().equals("void")) return false;
if(method.getParameterCount()!=0)return false;
return true;
}
private static boolean isUpperCaseAlphabet(char m){
return m>=65&&m<=90;
}
private static int getSetterMethodIndex(Method[] methods,Method method){
int i=-1;
String methodName="set"+method.getName().substring(3);
String returnType=method.getReturnType().getName(); 
Class<?>[] parameters;
for(Method m:methods){
++i;
if(!m.getName().equals(methodName)) continue;
if(m.getParameterCount()!=1) continue;
parameters=m.getParameterTypes();
if(!parameters[0].getName().equals(returnType)) continue;
return i;
}
return -1;
}
private static void invoke(Object targetObject,Method tMethod,Object sourceObject,Method sMethod){
try{
tMethod.invoke(targetObject,sMethod.invoke(sourceObject));
}catch(Throwable t){
}
}
}