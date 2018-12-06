import java.sql.*;
import java.util.Scanner;

class Controllerold {
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

	public static void newMenu() throws InterruptedException, SQLException {
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

	public static void inputHandler() throws InterruptedException, SQLException {
		String input = scan.nextLine();
		switch (input) {
		case "1": {
			try {
				queryDB("CREATE DATABASE " + dbname);
				newMenu();
			} catch (SQLException sqlEx) {
				System.out.println("Database already exists!");
				newMenu();
			}
			break;
		}
		case "2": {
			try {
				queryDB("CREATE TABLE department (Dlocation varchar(255),"
						+ "Dname varchar(255)," + "Dnumber int,"
						+ "Mgr_ssn int," + "PRIMARY KEY(Dnumber)" + ");");
				newMenu();
			} catch (SQLException sqlEx) {
				System.out.println("Department table already exists!");
				newMenu();
			}
			break;
		}
		case "3": {
			try {
				queryDB("CREATE TABLE project (Dnum int,"
						+ "Plocation varchar(255)," + "Pname varchar(255),"
						+ "Pnumber int," + "PRIMARY KEY(Pnumber)" + ");");
				newMenu();
			} catch (SQLException sqlEx) {
				System.out.println("Project table already exists!");
				newMenu();
			}
			break;
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
						queryDB("DELETE FROM employee WHERE ssn LIKE "
								+ getSSNFromQuery(query) + ";"); // Delete
																	// existing
																	// employee
						queryDB(query); // Create new employee like the existing
										// one
					} catch (SQLException e) {
						System.out.println(e);
						System.out.println("Returning to main menu...");
						Thread.sleep(3000);
						newMenu();
					}
				} else {
					newMenu();
				}
			} finally {
				newMenu();
			}
			break;
		}
		case "5": {
			System.out.println("Enter Employee SSN for deletion");
			String in = scan.nextLine();
			System.out
					.println("Are you sure you want to continue? The tuple you are trying to delete may have a foreign key. (y or n)");
			String overwrite = scan.nextLine();
			if (overwrite.toLowerCase().equals("y")) {
				try {
					queryDB("DELETE employee WHERE ssn LIKE " + in);
					newMenu();
				} catch (SQLException e) {
					newMenu();
				}
			} else {
				newMenu();
			}

		}
		case "6": {
			System.out.println("Enter Works_on info for deletion. <essn, pno>");
			String in = scan.nextLine();
			String essn = in.substring(0, in.indexOf(",")).trim();
			String pno = in.substring(in.indexOf(",")).trim();
			String query = "DELETE FROM works_on WHERE " + essn + ",  ;"; //Double Check This
			queryDB(query);
		}

		case "7": {

		}

		case "8": {

		}

		case "9": {

		}
		}
	}

	private static String getSSNFromQuery(String query) {
		String ssn = "";
		ssn = ssn.substring(ssn.indexOf(","));
		ssn = ssn.substring(ssn.indexOf(","));
		ssn = ssn.substring(0, ssn.indexOf(","));
		ssn.trim();
		return ssn;
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