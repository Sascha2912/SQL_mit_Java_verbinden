import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class BestellungController {


    public static void selectBestellung(){

        Connection connection = null;

        try{

            connection = MySQL.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from bestellung");

            while(rs.next()){
                int nummer = rs.getInt("nummer");

                int kundennummer = rs.getInt("kunde");
                Kunde kunde = Kunde.kunden.get(kundennummer);

                int rechnungsAdressnummer = rs.getInt("rechnungsadresse");
                Adresse rechnungsAdresse = Adresse.adressen.get(rechnungsAdressnummer);

                int lieferAdressnummer = rs.getInt("lieferadresse");
                Adresse lieferAdresse = Adresse.adressen.get(lieferAdressnummer);

                String datum = rs.getString("datum");

                new Bestellung(nummer, kunde, rechnungsAdresse, lieferAdresse, datum);

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

