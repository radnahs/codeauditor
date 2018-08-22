  package com.webtual.auditextor.parser;
  
  public class HeapInspection{
  
    public static void main(){
      System.out.println("hellp HeapInspection");
    }
  
  }
  class NonCompliantHeapInspection{
  
    private String password;
    
    public void setPassword(){
      password = System.console().readLine("Enter your password :");
    }
  }
  

  class CompliantHeapInspection{
  
    private SealedObject password;
    
    public void setPassword(){
      try{
      }catch(){}
      
      password = System.console().readLine("Enter your password :");
    }
  }
