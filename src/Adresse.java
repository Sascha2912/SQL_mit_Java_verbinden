import java.util.HashMap;

public class Adresse {

    public static final HashMap<Integer, Adresse> adressen = new HashMap<>();
    private final int id;
    private String straße;
    private int plz;
    private String ort;
    private int hausnummer;
    private final Kunde kunde;

    public Adresse(int id, String straße, int plz, String ort, int hausnummer, Kunde kunde ){

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
        if(AdresseController.updateAdresse(this, "straße", straße)){

        this.straße = straße;
        }
    }

    public int getPlz(){
        return this.plz;
    }
    public void setPlz(int plz){
        if(AdresseController.updateAdresse(this, "plz", plz)){
            this.plz = plz;
        }

    }

    public String getOrt(){
        return this.ort;
    }

    public void setOrt(String ort){
        if(AdresseController.updateAdresse(this, "ort", ort)){
            this.ort = ort;
        }

    }

    public int getHausnummer(){
        return this.hausnummer;
    }
    public void setHausnummer(int hausnummer){
        if(AdresseController.updateAdresse(this, "hausnummer", hausnummer)){
            this.hausnummer = hausnummer;
        }
    }

    public Kunde getKunde(){
        return this.kunde;
    }

}
