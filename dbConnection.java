package dbUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String SQCONN = "jdbc:sqlite::resource:Employee.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(SQCONN);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}

