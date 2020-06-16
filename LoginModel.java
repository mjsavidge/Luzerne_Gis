package loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class LoginModel {
    Connection connection;

    public LoginModel(){
        try{
            this.connection = dbConnection.getConnection();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        if(this.connection == null){
            System.exit(0);
        }
    }

    public boolean isDataBaseConnected(){

        return this.connection != null;
    }

    public boolean isLogin(String user, String pass, String admin)throws Exception{
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Employees WHERE user_name = ? AND password = ? AND admin = ?";

        try{
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            pr.setString(3, admin);

            rs = pr.executeQuery();
            boolean x = rs.next();

            //return rs.next();
            pr.close();
            rs.close();
            return x;
        }
        catch(SQLException ex){
            ex.printStackTrace();
            if(pr != null){
                pr.close();
            }
            if(rs != null){
                rs.close();
            }
            return false;
        }


    }
}
