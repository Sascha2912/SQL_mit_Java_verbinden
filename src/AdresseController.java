import java.sql.*;

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

    public static Adresse createAdresse(String straße, int plz, String ort, int hausnummer, Kunde kunde){
        Adresse newAdresse = null;

        try(
                Connection connection = MySQL.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO adresse (straße, plz, ort, hausnummer, kunde) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                );

        ){
            statement.setString(1, straße);
            statement.setInt(2, plz);
            statement.setString(3, ort);
            statement.setInt(4, hausnummer);
            statement.setInt(5, kunde.getNummer());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()){
                int id = rs.getInt(1);

                newAdresse = new Adresse(id,straße, plz, ort, hausnummer, kunde);
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return newAdresse;

    }

}
