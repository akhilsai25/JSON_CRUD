import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main_JSON {
	static int flag=0;
public static void main(String[] args) throws Exception
{ 
	  
	  Scanner sc=new Scanner(System.in);
      for(int i=0;i<10;i++)
      {
   	   System.out.println("Enter the specific option");
          System.out.println("Adding->1");
          System.out.println("printing details->2");
          System.out.println("update->3");
          System.out.println("delete->4");
          System.out.println("search for employee->5");
          int opt=sc.nextInt();
          switch(opt)
          {
          case 1:
         
   	         addEmployee();   
   	         break;
   	    
          case 2:
        	  
        	  printDetails();
        	  break;
   	      
          case 3:
        	  
        	  updateDetails();
        	  break;
        	  
          case 4:
        	  
        	  deleteEmployees();
        	  break;
        	  
          case 5:
        	  
        	  searchEmployees();
        	  break;
        	  
          case 6:
        	  System.exit(0);
        	  break;
          }
      }
          
          
}

          public static void addEmployee() throws Exception
          {
        	  Scanner sc=new Scanner(System.in);
      		System.out.println("Enter the Employee name");
      		StringBuffer name=new StringBuffer(sc.nextLine());
      		System.out.println("Enter the Employee age");
      		int age=sc.nextInt();
      		System.out.println("Enter the Employee sal");
      		int sal=sc.nextInt();
      		Random rand=new Random();
      		int id=rand.nextInt(1000)+1;
      		
      		//JSON adding
      	    JSONArray ja=(JSONArray) new JSONParser().parse(new FileReader("C:\\Users\\saiak\\eclipse-workspace\\JSON_casestudy\\src\\Employee.json"));
      		JSONObject jo=new JSONObject();
      		jo.put("id",id);
      		jo.put("name",name.toString());
      		jo.put("age",age);
      		jo.put("sal",sal);
      		ja.add(jo);
      		FileWriter fw=new FileWriter("C:\\Users\\saiak\\eclipse-workspace\\JSON_casestudy\\src\\Employee.json");
      		PrintWriter pw = new PrintWriter(fw); 
            pw.write(ja.toJSONString()); 
            pw.flush();
            pw.close();
          }
          
          public static void printDetails() throws Exception
          {
        	  System.out.println("-------------------details----------------------");
        	  JSONArray ja=(JSONArray) new JSONParser().parse(new FileReader("C:\\Users\\saiak\\eclipse-workspace\\JSON_casestudy\\src\\Employee.json"));
        	  //System.out.println(ja);
        	  ja.forEach(e -> parseObject((JSONObject)e));  
          }
          
          public static void parseObject(JSONObject jo)
          {
        	  String name = (String) jo.get("name");
        	  System.out.println("Name -> "+name);
        	  long id=(long)(jo.get("id"));
        	  long sal=(long) jo.get("sal");
        	  long age=(long) jo.get("age");
        	  System.out.println("id -> "+id);
      		//System.out.println("Name -> "+name);
      		System.out.println("age -> "+age);
      		System.out.println("sal -> "+sal);
          }
          
          public static void updateDetails()  
          {
        	  try
        	  {
        	  JSONArray ja=(JSONArray) new JSONParser().parse(new FileReader("C:\\Users\\saiak\\eclipse-workspace\\JSON_casestudy\\src\\Employee.json"));
        	  Scanner sc=new Scanner(System.in);
        	  System.out.println("Enter the id of the employee you want to modify");
        	  int id=sc.nextInt();
        	ja.forEach((e) -> parseObjectUpdate((JSONObject)e,id));
        	  try
        	  {
        	  if(flag==0)
        	  {
        		  flag=1;
        		  throw new EmpExc("Wron Emp ID. Please enter the correct ID");}
        	  }
        	  catch(EmpExc e)
        	  {
        		  
        		  System.out.println("excpetion occured"+e);
        	  }
        	 FileWriter fw=new FileWriter("C:\\Users\\saiak\\eclipse-workspace\\JSON_casestudy\\src\\Employee.json");
     		PrintWriter pw = new PrintWriter(fw); 
           pw.write(ja.toJSONString()); 
           pw.flush();
           pw.close();
        	  }
        	  catch(IOException | ParseException  e)
        	  {
        		  System.out.println("problem loading and parsing the file");
        	  }
          }
          
          public static void parseObjectUpdate(JSONObject jo,int id) 
          {
        	  if((long)jo.get("id")==(long)id)
        	  {
        		  flag=1;
        		  Scanner sc=new Scanner(System.in);
        		  System.out.println("Enter 1->name 2->age 3->sal");
        		  int ch=sc.nextInt();
        		  String name=(String)jo.get("name");
        		  if(ch==1)
        		  {
        			  System.out.println("enter the new name");
            		  String new_name=sc.nextLine();
        			 jo.replace("name",name);
        		  }
        		  else if(ch==2)
        		  {
        			  System.out.println("enter the new age");
            		  int new_age=sc.nextInt();
            		  jo.replace("age",new_age);
        		  }
        		  else if(ch==3)
        		  {
        			  System.out.println("enter the new sal");
            		  int new_sal=sc.nextInt();
            		  jo.replace("sal",new_sal);
        		  }
        		 
        	  }
        	
          }
          
          public static void deleteEmployees() throws Exception
          {
        	  Scanner sc=new Scanner(System.in);
        	  System.out.println("Enter the id of the employee you want to delete");
        	  int id=sc.nextInt();
        	  JSONArray ja=(JSONArray) new JSONParser().parse(new FileReader("C:\\Users\\saiak\\eclipse-workspace\\JSON_casestudy\\src\\Employee.json"));
        	  Iterator a=ja.iterator();
        	  int count=0;
        	  while(a.hasNext())
        	  {
        		  if(delete((JSONObject)a.next(),id))
        		  {
        			  a.remove();
        		  }
        		  }
         	 FileWriter fw=new FileWriter("C:\\Users\\saiak\\eclipse-workspace\\JSON_casestudy\\src\\Employee.json");
      		PrintWriter pw = new PrintWriter(fw); 
            pw.write(ja.toJSONString()); 
            pw.flush();
            pw.close(); 
            System.out.println("Employee deleted");
          }
          public static boolean delete(JSONObject jo,int id)
          {
        	  if((long)jo.get("id")==(long)id)
        	  {
        		  return true;
        	  }
        	  return false;
          }
          
          public static void searchEmployees() throws Exception
          {
        	  int flag=0;
        	  System.out.println("Enter the employee you want to search");
        	  Scanner sc=new Scanner(System.in);
        	  int id=sc.nextInt();
        	  JSONArray ja=(JSONArray) new JSONParser().parse(new FileReader("C:\\Users\\saiak\\eclipse-workspace\\JSON_casestudy\\src\\Employee.json"));
        	  Iterator a=ja.iterator();
        	  JSONObject jo;
        	  while(a.hasNext())
        	  {
        		  jo=(JSONObject)a.next();
        		  if((long)jo.get("id")==(long)id)
        		  {
        			  flag=1;
        			  System.out.println("name->"+jo.get("name"));
        			  System.out.println("age->"+jo.get("age"));
        			  System.out.println("id->"+jo.get("id"));
        			  System.out.println("sal->"+jo.get("sal"));
        		  }
        		  if(flag==1)
        		  {
        			  flag=0;
        			  throw new EmpExc("Wrong Emp ID. Please enter the correct ID");  
        		  }
        		  } 
          }
          }

