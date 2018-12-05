import java.sql.*;

class Main{
    final String dbname = "CSCI3410_LAB3";
    static Connection sqldb;
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        sqldb = Functions.getConnection();
    }



}