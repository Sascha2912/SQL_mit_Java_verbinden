import java.sql.*;

public class HerstellerController {

    public static void selectHersteller(){
        Connection connection = null;

        try{
            connection = MySQL.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from hersteller");

            while(rs.next()){

                int nummer = rs.getInt("nummer");
                String name = rs.getString("name");

                new Hersteller(nummer, name);



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

    public static Hersteller createHersteller(String name){

        Hersteller h = null;

        try(
                Connection connection = MySQL.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO hersteller (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS
                );
        ){
            statement.setString(1, name);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next() ){
                int nummer = rs.getInt(1);
                h = new Hersteller(nummer, name);
            }
            return h;
        }catch(SQLException ex){
            ex.printStackTrace();
            return h;
        }

    }






}
