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

    // Schnelle und präzise Abfrage aller Kunden um, die Kunden effizienter verwalten zu können.
    public static String selectKundenDaten(){

        try(
             Connection connection = MySQL.getConnection();
             Statement statement = connection.createStatement();
        ) {
            StringBuilder sb = new StringBuilder();
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM selectKundenDaten"
            );
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                sb.append(rs.getMetaData().getColumnName(i)).append(" | ");
            }
            sb.append("\n");
            while (rs.next()) {


                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
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

            if(statement.executeUpdate() > 0){

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

    public static int insertAdresse(String straße, int plz, String ort, int kunde, int hausnummer){

        try(
              Connection connection = MySQL.getConnection();
              CallableStatement statement = connection.prepareCall(
                      "CALL insertAdresse(?,?,?,?,?)"
              );
        ){
            statement.setString(1, straße);
            statement.setInt(2, plz);
            statement.setString(3, ort);
            statement.setInt(4, kunde);
            statement.setInt(5, hausnummer);

            statement.registerOutParameter(6, JDBCType.INTEGER);

            int anzahl = statement.executeUpdate();

            return anzahl;
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return -1;
    }


}
