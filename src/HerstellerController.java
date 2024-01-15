import java.sql.*;

public class HerstellerController {

    public static void selectHersteller() {
        Connection connection = null;

        try {
            connection = MySQL.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from hersteller");

            while (rs.next()) {

                int nummer = rs.getInt("nummer");
                String name = rs.getString("name");

                new Hersteller(nummer, name);


            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    public static Hersteller createHersteller(String name) {

        Hersteller newHersteller = null;

        try (
                Connection connection = MySQL.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO hersteller (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS
                );
        ) {
            statement.setString(1, name);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                int nummer = rs.getInt(1);
                newHersteller = new Hersteller(nummer, name);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return newHersteller;

    }

    public static boolean updateHersteller(Hersteller hersteller, String attribut, Object wert) {


        try(
        Connection connection = MySQL.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE hersteller SET " + attribut + " = ? WHERE nummer = " + hersteller.getNumber()
            )
        ){
            statement.setObject(1, wert);

            int anzahl = statement.executeUpdate();


            return anzahl == 1;

        }catch (SQLException ex){

            ex.printStackTrace();

            return  false;
        }

    }





}
