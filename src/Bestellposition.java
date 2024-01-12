import java.util.HashMap;

public class Bestellposition {

    public static final HashMap<Integer, Bestellposition> bestellpositionen = new HashMap<>();

    private final Bestellung bestellung;
    private final Artikel artikel;
    private int anzahl;

    Bestellposition(Bestellung bestellung, Artikel artikel, int anzahl){

        this.bestellung = bestellung;
        this.artikel = artikel;
        this.anzahl = anzahl;

        bestellpositionen.put(bestellung.getNummer() + artikel.getNummer(), this);

    }
    public Bestellung getBestellung(){
        return this.bestellung;
    }
    public Artikel getArtikel(){
        return this.artikel;
    }

    public int getAnzahl(){
        return this.anzahl;
    }

    public void setAnzahl(int anzahl){
        this.anzahl = anzahl;
    }
}
