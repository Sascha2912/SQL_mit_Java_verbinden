import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private static String connectionString = "jdbc:mysql://127.0.0.1:3306/onlineshop";

    public static void setConnectionString(String connectionString){
        MySQL.connectionString = connectionString;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, "root", "");
    }

}
