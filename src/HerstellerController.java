import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}
