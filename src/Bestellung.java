import java.util.HashMap;

public class Bestellung {

    public static final HashMap<Integer, Bestellung> bestellungen = new HashMap<>();

    private final int nummer;
    private final Kunde kunde;
    private final Adresse rechnungsadresse;
    private final Adresse lieferadresse;
    private String datum;

    Bestellung(int nummer, Kunde kunde, Adresse rechnungsadresse, Adresse lieferadresse, String datum){
        this.nummer = nummer;
        this.kunde = kunde;
        this.rechnungsadresse = rechnungsadresse;
        this.lieferadresse = lieferadresse;
        this.datum = datum;

        bestellungen.put(nummer, this);
    }

    public int getNummer(){
        return this.nummer;
    }

    public Kunde getKunde(){
        return this.kunde;
    }

    public Adresse getRechnungsadresse(){
        return this.rechnungsadresse;
    }
    public Adresse getLieferadresse(){
        return this.lieferadresse;
    }

    public String getDatum(){
        return this.datum;
    }
    public void setDatum(String datum){
        this.datum = datum;
    }

}
