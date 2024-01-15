import java.math.BigDecimal;
import java.sql.*;

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

    public static Artikel createArtikel(String bezeichnung, BigDecimal preis, Hersteller hersteller){
        Artikel newArtikel = null;

        try(
                Connection connection = MySQL.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO artikel (bezeichnung, preis, hersteller) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                );
        ){
            statement.setString(1, bezeichnung);
            statement.setBigDecimal(2, preis);
            statement.setInt(3,hersteller.getNumber());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if( rs.next() ){
                int nummer = rs.getInt(1);

                newArtikel = new Artikel(nummer, bezeichnung, preis, hersteller);
            }


        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return newArtikel;
    }

    public static boolean updateArtikel(Artikel artikel, String attribut, Object wert){

        try(
                Connection connection = MySQL.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "Update artikel SET " + attribut + " = ? WHERE nummer = " + artikel.getNummer()
                );
        ){
            statement.setObject(1, wert);

            int anzahl = statement.executeUpdate();

             return anzahl == 1;

        }catch(SQLException ex){
            ex.printStackTrace();

            return false;
        }
    }

}
