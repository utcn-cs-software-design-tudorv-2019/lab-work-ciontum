package BusinessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbConnection {
    private static final String USERNAME="root";
    private static final String PASSWORD="dbpass";
    private static final String CONN="jdbc:mysql://localhost:3306/mydb";
    private static final String SQCONN="jdbc:sqlite:school.sqlite";

    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(CONN,USERNAME,"");
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
