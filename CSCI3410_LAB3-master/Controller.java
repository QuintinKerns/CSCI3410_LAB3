import java.sql.*;
import java.util.*;

class Controller{
    public static void main(String args[]){
        try{
            Connection cn = getConnection();
            ResultSet rs = queryDB(cn);
            cn


            cn.close();
        }catch(Exception e){ System.out.println(e);}
        Scanner scan= new Scanner(System.in);

        //Create the database and initialize the tables.
        {System.out.println("Would you like to create a database for your employees?");
        String createData = scan.nextLine();
        if createData == 'yes'
                System.out.println("Would you like to initialize the project, and department tables?");
        else if createData == 'no'
                System.out.println("Thank you, have a good day!");
        else System.out.println("That is not a valid response, please enter 'yes' or 'no'");}

        //Add more information into the table.
        {System.out.println("Would you like to enter more information into the Database? y or n");
            (String continue = scan.nextLine();
        if continue == 'y' System.out.println("Would you like to add information to the employee or works on table? e or w");
        String whichTable = scan.nextLine();
        else if continue == 'n' System.out.println("Thank you, have a good day!");
                else System.out.println("That is an invalid input, please enter Y for yes, or N for no. ");)

                //Ask the person to enter the information that they want to add into the table.
            (if whichTable == 'w'
                System.out.println("What is the ESSN, the Pno, and the number of Hours? Enter as a comma separated list.");
        else if whichTable == 'e'
                System.out.println("What is the employees First Name, Last Name, SSN, Address, and Dno? Enter as a comma separated list.");
        else System.out.println("That is not a valid response, please select 'e' for employee or 'w' for Works_On");

        String EmployeeInfo;
        EmployeeInfo = scan.nextLine();

        //if EmployeeInfo exists
            (System.out.println("This employee already exists in the table, would you like to cancel the operation or continue?");
        String DupEmp = scan.nextLine();
        if DupEmp == 'Cancel'
                System.out.println("This action has been canceled. Have a great day!");
        else if DupEmp == 'Continue'
                System.out.println("This has caused the employee information to be updated.");
        else System.out.println("That is not a valid response, please enter 'cancel' or 'continue'");)

    }

    public Connection getConnection(){
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root");
    }

    public ResultSet queryDB(String query, Connection cn){
        return cn.createStatement().executeQuery(query);
    }
}