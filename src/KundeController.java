import java.sql.*;

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

    public static Kunde createKunde(String name){

        Kunde newKunde = null;

        try(
                Connection connection = MySQL.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO kunde (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS
                );
        ){

            statement.setString(1, name);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if( rs.next()){
                int nummer = rs.getInt(1);

                newKunde = new Kunde(nummer, name);
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return newKunde;

    }

}
