import java.sql.*;

class Controller{
    public static void main(String args[]){
        try{
            Connection cn = getConnection();
            ResultSet rs = queryDB(cn);
            cn


            cn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public Connection getConnection(){
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root");
    }

    public ResultSet queryDB(String query, Connection cn){
        return cn.createStatement().executeQuery(query);
    }
}