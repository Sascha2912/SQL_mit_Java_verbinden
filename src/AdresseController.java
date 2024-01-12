import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AdresseController {

    public static void selectAdresse(){

        Connection connection = null;

        try{

            connection = MySQL.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from adresse");

            while(rs.next()){
                int id = rs.getInt("id");
                String straße = rs.getString("straße");
                int plz = rs.getInt("plz");
                String ort = rs.getString("ort");
                int hausnummer = rs.getInt("hausnummer");
                // Primarykey einbinden:
                // // In kundennummer wird die id aus der spalte kunde in der Tabelle adresse gespeichert.
                int kundennummer = rs.getInt("kunde");
                // Über die kundennummer das konkrete kunden-Objekt aus der HashMap auslesen, um es dem Konstruktor zu übergeben.
                Kunde kunde = Kunde.kunden.get(kundennummer);

                new Adresse(id, straße, plz, ort, hausnummer, kunde);

            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }finally {
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
