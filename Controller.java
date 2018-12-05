import java.sql.*;
import java.util.Scanner;

class Controller {
	public static final String dbname = "csci3410_lab3";
	public static final String dbuser = "root";
	public static final String dbpass = "root";
	static Scanner scan = new Scanner(System.in);

	static Connection conn;

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, InterruptedException {
		conn = getConnection();
		System.out.println("--- Connected ---");
		newMenu(); // Empties the console and creates a new menu and input
					// handler
	}

	public static void newMenu() throws InterruptedException {
		System.out.flush();
		printMainMenu();
		inputHandler();
	}

	public static void printMainMenu() {
		System.out.println(" Main Menu \n" + "-----------\n"
				+ "1. Create DB and Tables\n" + "2. Create Department Table\n"
				+ "3. Create Project Table\n" + "4. Insert Employee\n"
				+ "5. Insert Works_on\n" + "6. Remove Employee by ssn\n"
				+ "7. Remove Works_on by essn and pno\n" + "8. Query DB\n"
				+ "9. Fetch Metadata\n");
	}

	public static void inputHandler() throws InterruptedException {
		String input = scan.nextLine();
		switch (input) {
		case "1": {
			try {
				queryDB("CREATE DATABASE " + dbname);
			} catch (SQLException sqlEx) {
				System.out.println("Database already exists!");
			}
		}
		case "2": {
			try {
				queryDB("CREATE TABLE department (Dlocation varchar(255),"
						+ "Dname varchar(255)," + "Dnumber int,"
						+ "Mgr_ssn int," + "PRIMARY KEY(Dnumber)" + ");");
			} catch (SQLException sqlEx) {
				System.out.println("Department Table already exists!");
			}
		}
		case "3": {
			try {
				queryDB("CREATE TABLE project (Dnum int,"
						+ "Plocation varchar(255)," + "Pname varchar(255),"
						+ "Pnumber int," + "PRIMARY KEY(Pnumber)" + ");");
			} catch (SQLException sqlEx) {
				System.out.println("Department Table already exists!");
			}
		}
		case "4": {
			String query = "";
			try {
				System.out
						.println("Enter Employee Information seperated by commas. <First name, Last name, Ssn, Address, Dno>");
				String in = scan.nextLine();
				query = "INSERT INTO employee VALUES (" + in + ");";
				queryDB(query);
			} catch (SQLException sqlEx) {
				System.out
						.println("Employee already exists or query was entered incorrectly. Continue anyway? (y or n) This will overwrite existing employee data");
				String overwrite = scan.nextLine();
				if (overwrite.equals("y")) {
					try {
						queryDB("DELETE");
					} catch (SQLException e) {
						System.out.println(e);
						System.out.println("Returning to main menu...");
						Thread.sleep(3000);
						newMenu();
						
					}
				} else {
					newMenu();
				}
			}
		}
		case "5": {

		}

		}
	}

	public static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		// Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/");
	}

	public static ResultSet queryDB(String query) throws SQLException {
		return conn.createStatement().executeQuery(query);
	}

}