import java.sql.*;

public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/testingschema";
    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "DBadmin!1";
    Connection conn = null;

    public Connection dbConnect() throws SQLException {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ConnectionException) {
            ConnectionException.printStackTrace();
        }
        return conn;
    }

    public void dbEndConnect() throws SQLException{
        if (conn != null){
            conn.close();
        }
    }
}