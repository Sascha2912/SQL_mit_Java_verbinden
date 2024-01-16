import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    public static String selectAll(){

        String build = "test";
        try(
                Connection connection = MySQL.getConnection();
                Statement statement = connection.createStatement();

        ){

            ResultSet rs = statement.executeQuery("select * from selectall");

            StringBuilder sb = new StringBuilder();

            sb.append("Header\n");

            while(rs.next()){
                sb.append(rs.getInt("kunden_nummer"));
                sb.append(rs.getString("kunden_name"));
                sb.append(rs.getInt("bestellung"));
                sb.append(rs.getString("datum"));
                sb.append(rs.getString("rechnungs_straße"));
                sb.append(rs.getInt("rechnungs_hausnummer"));
                sb.append(rs.getString("liefer_straße"));
                sb.append(rs.getInt("liefer_hausnummer"));
                sb.append(rs.getInt("artikel"));
                sb.append(rs.getString("bezeichnung"));
                sb.append(rs.getInt("anzahl"));
                sb.append(rs.getInt("hersteller"));
                sb.append(rs.getString("hersteller_name"));

                System.out.println(sb);
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return build;
    }


}
