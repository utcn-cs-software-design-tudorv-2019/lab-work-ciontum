package Model;

import BusinessLayer.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;
    public LoginModel(){
        try{
            this.connection= dbConnection.getConnection();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        if(this.connection==null)
        {
            System.exit(1);
        }
    }
    public boolean isDataBaseConnected(){
        return this.connection !=null;
    }
    public boolean isLogin(String user,String pass) throws  Exception{
        PreparedStatement pr =null;
        ResultSet rs=null;
        String sql="SELECT username,pass FROM userl WHERE username = ? AND pass = ?";
        try{
            pr=this.connection.prepareStatement(sql);
            pr.setString(1,user);
            pr.setString(2,pass);

            rs=pr.executeQuery();
            boolean boll1;
            if(rs.next())
                return true;
            return false;
        }
        catch(SQLException ex) {
            return false;
        }
        finally {
            pr.close();
            rs.close();
        }

    }

}
