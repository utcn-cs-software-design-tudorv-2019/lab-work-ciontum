package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbConnection {
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static final String CONN="jdbc:mysql://localhost:3306/mydb";


    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(CONN,USERNAME,PASSWORD);
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
