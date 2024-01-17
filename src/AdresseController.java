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

    /*
    public static Adresse createAdresse(String straße, int plz, String ort, int hausnummer, Kunde kunde){
        Adresse newAdresse = null;

        try(
                // Verbindung zur Datenbank herstellen
                Connection connection = MySQL.getConnection();
                // Vorläufiges Statement definieren und im Objekt statement speichern.
                PreparedStatement statement = connection.prepareStatement(
                        // SQL-Befehl mit angabe der Tabelle "adresse" und angabe der Spalten "straße, plz, ort, hausnummer, kunde" mit noch nicht definierten VALUES "?, ?, ?, ?, ?"
                        // auslesen des Primary-Keys durch den Befehl "Statement.RETURN_GENERATED_KEYS"
                        "INSERT INTO adresse (straße, plz, ort, hausnummer, kunde) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                );

        ){
            // Reihenfolge und Werte der vorläufigen Values zuweisen.
            statement.setString(1, straße);
            statement.setInt(2, plz);
            statement.setString(3, ort);
            statement.setInt(4, hausnummer);
            statement.setInt(5, kunde.getNummer());

            // fertiges SQL-Statement ausführen.
            // Neuen Datensatz mit dem Übergabe-Parameter der Funktion in der SQL-Datenbank Tabelle adresse erstellen.
            statement.executeUpdate();

            // Datenbankabfrage des Primary-Keys im Objekt rs speichern
            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()){
                // Primary-Key aus dem Objekt rs auslesen und in der int Variablen id abspeichern.
                int id = rs.getInt(1);

                // Neues adressenObjekt erstellen mit den Übergabe-Parametern aus der Funktion. Das Objekt ist ein Abbild des neu erzeugten Datensatzes in der SQL-Datenbank Tabelle adresse.
                newAdresse = new Adresse(id,straße, plz, ort, hausnummer, kunde);
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return newAdresse;

    }
     */

    public static Adresse createAdresse(String straße, int plz, String ort, int hausnummer, Kunde kunde){

        Adresse newAdresse = null;

        int id = SQLController.insertAdresse(straße, plz, ort, kunde.getNummer(), hausnummer);

        if(id > 0 ){
            newAdresse = new Adresse(id, straße, plz,ort,hausnummer,kunde);

            return newAdresse;
        }
        return newAdresse;
    }


    public static boolean updateAdresse(Adresse adresse, String attribute, Object wert){

        try(
             Connection connection = MySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE adresse SET " + attribute + " = ? WHERE id = " + adresse.getId()
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
