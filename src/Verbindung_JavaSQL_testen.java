import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class Verbindung_JavaSQL_testen {

        public static void main(String[] args)
        {
            Connection connection = null;
            try
            {
                String connectionString = "jdbc:mysql://localhost:3306/onlineshop";
                connection = DriverManager.getConnection(connectionString, "root", "");

                System.out.println("Got it!");

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from artikel");
                while(rs.next())
                {
                    String sb = rs.getInt("nummer") +
                            " " + rs.getString("bezeichnung") +
                            " " + rs.getDouble("preis") +
                            " " + rs.getInt("hersteller");

                    System.out.println(sb);
                }
                connection.close();

            }
            catch (SQLException e)
            {
                System.out.println(e);
            }
        }

}
