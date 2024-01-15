import java.sql.*;

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

    public static Bestellung createBestellung(Kunde kunde, Adresse rechnungsadresse, Adresse lieferadresse, String datum){
        Bestellung newBestellung = null;

        try(
                Connection connection = MySQL.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO bestellung (datum, kunde, rechnungsadresse, lieferadresse) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                );

        ){
            statement.setString(1, datum);
            statement.setInt(2, kunde.getNummer());
            statement.setInt(3,rechnungsadresse.getId());
            statement.setInt(4, lieferadresse.getId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()){
                int nummer = rs.getInt(1);

                newBestellung = new Bestellung(nummer, kunde, rechnungsadresse, lieferadresse, datum);
            }


        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return newBestellung;
    }

    public static boolean updateBestellung(Bestellung bestellung, String attribut, Object wert){

        try(
                Connection connection = MySQL.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE bestellung SET " + attribut + " = ? WHERE nummer = " + bestellung.getNummer()
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

