import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bestellposition {

    public static final List<Bestellposition> bestellpositionen = new ArrayList<>();

    private final Bestellung bestellung;
    private final Artikel artikel;
    private int anzahl;

    Bestellposition(Bestellung bestellung, Artikel artikel, int anzahl){

        this.bestellung = bestellung;
        this.artikel = artikel;
        this.anzahl = anzahl;

        bestellpositionen.add(this);

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
