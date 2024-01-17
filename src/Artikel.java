import java.math.BigDecimal;
import java.util.HashMap;

public class Artikel {

    public static final HashMap<Integer, Artikel> artikel = new HashMap<>();

    private final int nummer;
    private String bezeichnung;
    private BigDecimal preis;
    private final Hersteller hersteller;

    public Artikel(int nummer, String bezeichnung, BigDecimal preis, Hersteller hersteller){
        this.nummer = nummer;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.hersteller = hersteller;

        artikel.put(nummer, this);
    }

    public int getNummer(){
        return this.nummer;
    }

    public String getBezeichnung(){
        return this.bezeichnung;
    }

    public void setBezeichnung(String bezeichnung){
        if(ArtikelController.updateArtikel(this,"bezeichnung",bezeichnung)){
            this.bezeichnung = bezeichnung;
        }

    }

    public BigDecimal getPreis(){
        return this.preis;
    }

    public void setPreis(BigDecimal preis){
        if(ArtikelController.updateArtikel(this, "preis", preis)){

            this.preis = preis;
        }

    }

    public void setArtikelPreis(BigDecimal preis) {
        if(Controller.updateArtikelPreis(this.nummer, preis) > 0){

            this.preis = preis;
        }
    }


    public Hersteller getHersteller(){
        return this.hersteller;
    }



}
