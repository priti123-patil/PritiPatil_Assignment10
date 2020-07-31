//step1:import all the necessary packages

import java.sql.*;         //this package is imported for sql connection
import java.util.*;        //this package is imported to use scanner
import java.io.*;          //this package is imported to perform file operations
public class jdbcex        //declaration of jdbcex class 
{                          //curly bracket start
 //JDBC driver name and database URL
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";          //declaration of static variable JDBC_DRIVER and value initialized to it
static final String DB_URL = "jdbc:mysql://localhost:3306/db_bank"; //declaration of static variable DB_URL,databse path initialized to it
 //Database Credentials
static final String USER = "root";                                  //declaration of static variable USER and value initialized to it
static final String PASS = "";                                      //declaration of static variale PASS and blankvalue assigned to it
public static void main(String[] args)                              //declaration of main method,execution starts from here
{ 
 //In this program two tables are created tbl_transaction and tbl_account
 //tbl_transaction(acc_no,transaction_date,ammount,transaction_type,balance)
 //tbl_account(acc_no,name,age,address,opening_balance_ammount)
   
   System.out.println("**************************BANK MANAGEMENT SYSTEM******************************");
   while(true)
   {
   System.out.println("1.create account.");
   System.out.println("2.Transaction.");
   System.out.println("3.printData");
   System.out.println("4.Delete Account");
   Scanner s=new Scanner("System.in");                                //initializing scanner to accept values
   System.out.println("Enter your choice");
   int choice=s.nextInt();
   switch(choice)
   {
   case 1:
   System.out.println("Enter your account number");                   //prints the double quoted text on output console screen
   int accno=s.nextInt();                                             //scan acc_no entered by user and strore it in accno variable
   System.out.println("Enter your name");                             //prints the double quoted text on output console screen
   String name=s.nextLine();                                          //scan name entered by user and store it in name variable
   System.out.println("Enter your age");                              //prints the double quoted text on output console screen
   int age=s.nextInt();                                               //scan age entered by user and store it in age variable
   System.out.println("Enter your address");                          //prints the double quoted text on output console screen
   String address=s.nextLine();                                       //scan address entered by user and store it in address variable
   System.out.println("Enter opening balance amount");                //prints the double quoted text on output console screen
   int opbalam=s.nextInt();                                           //scan open_bal_amount entered by user and store it in opbalam variable
   saveDetails(accno,name,age,address,opbalam);                       //calling saveDetails()function to insert the values in tbl_account
   break;
   case 2:
   System.out.println("Enter account no");                            //prints the double quoted text on output console screen
   int accno2=s.nextInt();                                            //scan acc_no entered by user and strore it in accno2 variable
   System.out.println("Enter transaction date");                      //prints the double quoted text on output console screen
   String date=s.nextLine();                                          //scans transaction date entered by user and store it in age variable 
   System.out.println("Enter amount");                                //prints the double quoted text on output console screen
   int amount=s.nextInt();                                            //scan amount entered by user and store it in amount variable
   System.out.println("Enter transaction type");                      //prints the double quoted text on output console screen
   String trantype=s.nextLine();                                      //scan transaction type entered by user and store it in trantype variable   
   System.out.println("Enter your account balance");                  //prints the double quoted text on output console screen
   int balance1=s.nextInt();                                          //scans account balance entered by user and store it in balance1 variable
   try 
   {
   transaction(accno2,date,amount,trantype,balance1);                  //calling transaction() function to insert values and to perform transactions in tbl_transaction 
   } 
   catch (Exception e)
   {
   e.printStackTrace();
   }                  
   break;
   case 3:
   //calling printData() function to write all this details in a new text file 
   printData();
   break;
   case 4:
   deleteAccount();
   break;
   default:
   //prints the double quoted text on output console screen
   System.out.println("Goodbye!");
   }
   }
}   //end of the main() function

//saveDetails() function defination starts here
public static void saveDetails(int accno,String name,int age,String address,int opbalam)
{
//here try,catch block is used because SQLException,Exception can occure in the following statements
 try
 { 
  Statement stmt=null;          //declaring statement and initializing null value to it
  Connection conn = null;       //declaring connection and initializing null value to it to establish a connection with sql
  System.out.println("Connecting to a selected database...");
  //Register JDBC driver
  conn = DriverManager.getConnection(DB_URL, USER, PASS); 
  //Open a database connection
  System.out.println("Connected database successfully...");
  Class.forName("com.mysql.jdbc.Driver");
  stmt = conn.createStatement();
  System.out.println("Inserting records into the table..."); 
  String sql = "INSERT INTO tbl_account VALUES(accno,name,age,address,opbalam)";
  //execute a query
  stmt.executeUpdate(sql);
  System.out.println("Inserted records into the tbl_account table...");  
 }
 //if SQLException occurs in the above statements then this catch statement catches it and prints the exception
 catch(SQLException se)
 {
     se.printStackTrace();
 }
 //if Exception occurs in the above statements then this catch statement catches it and prints the exception
 catch(Exception e)
 {
     e.printStackTrace();
 } 
 System.out.println("Account Successfully creadted");  
} //end of saveDetails function

//transactio function defination starts here
public static void transaction(int accno1,String date1,int amount,String trantype,int balance1) throws Exception
{ 
 String test=null;
 test.toString();
 //here try,catch block is used because SQLException,Exception can occure in the following statements
 try 
 {
 Statement stmt1 = null;
 Connection conn3 = null;
 System.out.println("Connecting to a selected database...");
 //Register JDBC driver
 conn3 = DriverManager.getConnection(DB_URL, USER, PASS);
 //Open a database connection
 System.out.println("Connected database successfully...");
 Class.forName("com.mysql.jdbc.Driver");
 stmt1 = conn3.createStatement();
 System.out.println("Inserting records into the table...");
 String sql2="INSERT INTO `tbl_transaction` VALUES(accno1,date1,amount, trantype, balance1)";
 stmt1.executeUpdate(sql2);
 System.out.println("Inserted records into the table...");
 //deposit is initialized to a new string
 String str3="deposit";
 //now this if-else checkes whether the trantype is deposit or withdraw if it is deposit then it adds the amount to the current balance 
 //and if it is withdraw then minus the amount from current balance 
 if(trantype.equals(str3))
 {
 balance1=balance1+amount;  //adds the amount to balance(deposit)
 }
 else
 {
  balance1=balance1-amount; //minuses the amount from balance(withdraw)
      //if amount is greater than balance then it throws an new exception
      if(balance1<amount)
      {
      throw new Exception("please check the ammount! Low balance");
      }
 }
 //now after deposit and withdraw value of balance is changed so we have to update it for that here update query is written
 sql2="UPDATE `tbl_transaction` SET `balance`=balance1 WHERE 1";
 stmt1.executeUpdate(sql2);
 }
 //handles error for JDBC
 catch(SQLException se)
 {
     se.printStackTrace();
 }
 catch(Exception e)
 {
     e.printStackTrace();
 }
 System.out.println("Transaction Successfully done!");
} //end of the transaction function

//printData() method definition starts here 
public static void printData()
 { 
try
{  
   Connection conn5 = null;
   Statement stmt5 = null;
   Class.forName("com.mysql.jdbc.Driver");
   System.out.println("Connecting to a selected database...");
   conn5 = DriverManager.getConnection(DB_URL, USER, PASS);
   System.out.println("Connected database successfully...");
   System.out.println("Creating statement...");
   stmt5 = conn5.createStatement();
   String sql5="SELECT accno,name,age,address,opbalam,date,trantype,amount from tbl_account inner join tbl_transaction on tbl_account.accno=tbl_transaction";
   ResultSet rs = stmt5.executeQuery(sql5);
   try
   {
      //create new file named as jdbcfile
      File obj = new File("jdbcfile.txt");
      //if file already created then it prints file already exists otherwise prints file created:jdbcfile
      if (obj.createNewFile()) 
      {
        System.out.println("File created: " + obj.getName());
      } 
      else
      {
        System.out.println("File already exists.");
      }
      rs.next();
      Clob c=rs.getClob(2);  
      Reader r=c.getCharacterStream();              
      FileWriter fw=new FileWriter("jdbcfile.txt");  
      int i;  
      while((i=r.read())!=-1)  
      fw.write((char)i);  
      fw.close();  
      System.out.println("Successfully wrote to the file.");
   } 
   //handles file operation errors
   catch (Exception e) 
   {
      System.out.println("An error occurred.");
      e.printStackTrace();
   }
  }
  catch(Exception e)
  {
  }
} //end of printdata() function defination

//deleteAccount() function defination starts here
public static void deleteAccount()
{
 try
 {
 Statement stmt4 = null;
 Connection conn1 = null;
 System.out.println("Connecting to a selected database...");
 //Register JDBC driver
 Class.forName("com.mysql.jdbc.Driver");
 conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
 //Open a database connection
 System.out.println("Connected database successfully...");
 stmt4 = conn1.createStatement();
 Scanner sn=new Scanner(System.in);
 System.out.println("Do you really want to delete account? if yes enter account_no");
 int no=sn.nextInt();
 String sql4="DELETE FROM `tbl_transaction` WHERE accno=no";
 //execute query
 stmt4.executeUpdate(sql4);
 }
 //handles jdbc errors
 catch(SQLException se)
 {
     se.printStackTrace();
 }
 catch(Exception e)
 {
     e.printStackTrace();
 }
 //if(jdbcfile.delete()) 
        //{ 
           //System.out.println("File deleted successfully"); 
       // } 
       // else
        //{ 
           // System.out.println("Failed to delete the file"); 
       // } 
}
//end of deleteAccount() function 
}//end of class