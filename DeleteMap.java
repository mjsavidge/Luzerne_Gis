package workers;

import dbUtil.dbMapConnection;

import java.sql.*;

public class DeleteMap {
    Connection connection;

    public DeleteMap(){
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

    public boolean removeMap(String name) throws Exception{
        PreparedStatement pr = null;
        int rs = 0;
        String sql = "DELETE FROM Map WHERE map_name = ?";

        try{
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, name);

            rs = pr.executeUpdate();
            //return rs.next();
        }
        catch(SQLException ex){
            return false;
        }
        finally {
            pr.close();
            //rs.close();
        }
        return false;
    }
}
