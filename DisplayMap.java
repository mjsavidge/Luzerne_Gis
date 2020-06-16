package workers;

import java.sql.*;

import dbUtil.dbMapConnection;

public class DisplayMap {
    Connection connection;

    public DisplayMap(){
        try{
            this.connection = dbMapConnection.getConnection();
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


}
