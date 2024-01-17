import java.math.BigDecimal;
import java.sql.*;

public class SQLController {

    public static String selectAll(){



        try(
                Connection connection = MySQL.getConnection();
                Statement statement = connection.createStatement();

        ){
            StringBuilder sb = new StringBuilder();

            ResultSet rs = statement.executeQuery("select * from selectall");



            // sb.append("Name -          Datum        - Rechnungsadresse - Lieferadresse - Artikel - Anzahl - Hersteller\n");
            for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                sb.append(rs.getMetaData().getColumnName(i)).append(" | ");
            }
            sb.append("\n");
            while(rs.next()){
                // sb.delete(0,sb.length());
                /*
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
                */

                /*
                sb.append(rs.getString("kunden_name")
                ).append(" - ");
                sb.append(rs.getString("datum")
                ).append(" - ");
                sb.append(rs.getString("rechnungs_straße")
                ).append(" ");
                sb.append(rs.getInt("rechnungs_hausnummer")
                ).append(" - ");
                sb.append(rs.getString("liefer_straße")
                ).append(" ");
                sb.append(rs.getInt("liefer_hausnummer")
                ).append(" - ");
                sb.append(rs.getString("bezeichnung")
                ).append(" x ");
                sb.append(rs.getInt("anzahl")
                ).append(" - ");
                sb.append(rs.getString("hersteller_name"));
                */

                sb.append(" | ");
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){

                //System.out.println(rs.getMetaData().getColumnLabel(i) + " - " + rs.getMetaData().getColumnName(i) + "\n");
                sb.append(rs.getString(i)).append(" | ");
                }
                sb.append("\n");

            }
            return sb.toString();

        }catch(SQLException ex){

            ex.printStackTrace();
        }

        return "";
    }

    public static int insertArtikel(String bezeichnung, BigDecimal preis, int hersteller){

        try(
                Connection connection = MySQL.getConnection();
                CallableStatement statement = connection.prepareCall(
                "CALL insertArtikel(?, ?, ?, ?)")
        ){
            statement.setString(1, bezeichnung);
            statement.setBigDecimal(2, preis);
            statement.setInt(3, hersteller);

            statement.registerOutParameter(4, JDBCType.INTEGER);

            int ergebnis = statement.executeUpdate();

            System.out.println("Anzahl Ergebniszeilen: " + ergebnis);

            if(ergebnis > 0){
                // System.out.println("Test\n" + );
               return statement.getInt(4);

            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return -1;
    }

    public static int updateArtikelPreis(int artikelNummer, BigDecimal preis){

        try(
             Connection connection = MySQL.getConnection();
             CallableStatement statement = connection.prepareCall(
                     "Call updateArtikelPreis(?, ?)"
             )
        ){
            statement.setInt(1, artikelNummer);
            statement.setBigDecimal(2, preis);

            int ergebnis = statement.executeUpdate();
                // Artikel.artikel.get(artikelNummer).setPreis(preis);
                // ArtikelController.updateArtikel(Artikel.artikel.get(artikelNummer)., "preis", preis);
            return ergebnis;


        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return -1;
    }


}
