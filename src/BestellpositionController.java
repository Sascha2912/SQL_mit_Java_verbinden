import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class BestellpositionController {

    public static void selectBestellposition(){

        Connection connection = null;

        try{
            connection = MySQL.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from bestellposition");

            while(rs.next()){
                int bestellungsNummer = rs.getInt("bestellung");
                Bestellung bestellung = Bestellung.bestellungen.get(bestellungsNummer);

                int artikelNummer = rs.getInt("artikel");
                Artikel artikel = Artikel.artikel.get(artikelNummer);

                int anzahl = rs.getInt("anzahl");

                new Bestellposition(bestellung, artikel, anzahl);
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
