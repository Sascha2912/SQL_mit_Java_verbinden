import java.sql.*;

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

    public static Bestellposition createBestellposition(Bestellung bestellung, Artikel artikel, int anzahl){
        Bestellposition newBestellposition = null;

        try(
                Connection connection = MySQL.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO bestellposition (bestellung, artikel, anzahl) VALUES (?, ?, ?)"
                );
        ){
            statement.setInt(1, bestellung.getNummer());
            statement.setInt(2, artikel.getNummer());
            statement.setInt(3, anzahl);
            statement.executeUpdate();

            newBestellposition = new Bestellposition(bestellung, artikel, anzahl);

        }catch(SQLException ex){
            ex.printStackTrace();
        }


        return newBestellposition;
    }


    public static boolean updateBestellposition(Bestellposition bestellposition, String attribute, Object wert){

        try(
              Connection connection = MySQL.getConnection();
              PreparedStatement statement = connection.prepareStatement(
                      "UPDATE bestellposition SET " + attribute + " = ? WHERE bestellung = " + bestellposition.getBestellung().getNummer() + " AND artikel = " + bestellposition.getArtikel().getNummer()
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
