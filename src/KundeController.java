import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KundeController {

    public static void selectKunde(){

        // Erstellung einer Connection
        Connection connection = null;

        try{
            // Der Connection über die Methode getConnection aus der Klasse MySQl mit der Datenbank verbinden
            connection = MySQL.getConnection();

            // statement Objekt erstellen, um statements für die Datenbank zu definieren.
            Statement statement = connection.createStatement();

            // SQL statements definieren ausführen
            // alle Datensatzzeilen aus der Tabelle kunde auslesen und in rs abspeichern.
            ResultSet rs = statement.executeQuery("select * from kunde");

            while(rs.next()){
                int nummer = rs.getInt("nummer");
                String name = rs.getString("name");

                new Kunde(nummer, name);
            }


        }catch(SQLException ex){
            ex.printStackTrace();

        }finally{
            if(connection != null){
                try{
                    connection.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }

            }

        }

    }

}
