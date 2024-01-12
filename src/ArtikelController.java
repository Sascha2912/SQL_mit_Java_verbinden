import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArtikelController {

    public static void selectArtikel(){
        Connection connection = null;

        try{

            connection = MySQL.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from artikel");

            while(rs.next()){
                int nummer = rs.getInt("nummer");
                String bezeichnung = rs.getString("bezeichnung");
                BigDecimal preis = rs.getBigDecimal("preis");
                int herstellerNummer = rs.getInt("hersteller");

                Hersteller hersteller = Hersteller.hersteller.get(herstellerNummer);

                new Artikel(nummer, bezeichnung, preis, hersteller);
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
