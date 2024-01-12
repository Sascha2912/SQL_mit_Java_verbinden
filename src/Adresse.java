import java.util.HashMap;

public class Adresse {

    public static final HashMap<Integer, Adresse> adressen = new HashMap<>();
    private int id;
    private String straße;
    private int plz;
    private String ort;
    private int hausnummer;
    private int kunde;

    public Adresse(int id, String straße, int plz, String ort, int hausnummer, int kunde ){

        this.id = id;
        this.straße = straße;
        this.plz = plz;
        this.ort= ort;
        this.hausnummer = hausnummer;
        this.kunde = kunde;

        adressen.put(id, this);

    }
    public int getId(){
        return this.id;
    }

    public String getStraße(){
        return this.straße;
    }
    public void setStraße(String straße){
        this.straße = straße;
    }

    public int getPlz(){
        return this.plz;
    }
    public void setPlz(int plz){
        this.plz = plz;
    }

    public String getOrt(){
        return this.ort;
    }

    public void setOrt(String ort){
        this.ort = ort;
    }

    public int getHausnummer(){
        return this.hausnummer;
    }
    public void setHausnummer(int hausnummer){
        this.hausnummer = hausnummer;
    }

    public int getKunde(){
        return this.kunde;
    }

}
