package workers;

import java.sql.*;

import dbUtil.dbMapConnection;
public class InsertMap {
    Connection connection;

    public InsertMap(){
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

    public boolean uploadMap(String tif, String tfwx, String aux, String ovr, String name) throws Exception{

        PreparedStatement pr = null;
        int rs = 0;
        String sql = "INSERT INTO Map (map_image, map_tfwx, map_aux, map_ovr, map_name) VALUES (?,?,?,?,?)";

        try{
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, tif);
            pr.setString(2, tfwx);
            pr.setString(3, aux);
            pr.setString(4, ovr);
            pr.setString(5, name);

            rs = pr.executeUpdate();
            //return rs.next();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            return  false;
        }
        finally {
            pr.close();
            //rs.close();
        }
        return false;
    }
}
