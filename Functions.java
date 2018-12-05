import java.sql.*;
class Functions{
	public static final String dbname = "CSCI3410_LAB3";
	public static final String dbuser = "root";
	public static final String dbpass = "root";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname + "," + dbuser + "," + dbpass);
    }
	
	// Menu 1: Init DB
    public void Step1(int optionNum){
        switch (optionNum){
            case 1: //Init DB
            {
                Connection conn = getConnection();
                ResultSet res = queryDB("CREATE DATABASE IF NOT EXISTS " + dbname, conn);
                console.log(res);
                conn.close();
                break;
            }
            case 2: //Create Dept Table
            {

            }
            case 3: //Create Proj Table
            {

            }
        }
    }

    // Menu 2: Insert Employee
    public void Opt2(){

    }

    // Menu 3: Insert Works_on
    public void Opt3(){

    }

    // Menu 4: Remove Employee by SSN
    public void Opt4(){

    }

    // Menu 5: Remove Works_on by essn and pno
    public void Opt5(){

    }

    // Menu 6: Query DB
    public void Opt6(){

    }

    // Menu 7: Fetch Metadata
    public void Opt7(){

    }
}