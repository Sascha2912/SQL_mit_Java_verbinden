public class Onlineshop {


    public static void main(String[] args) {


        // Liest die Daten aus der SQL-Tabelle Hersteller aus und erstellt für jede Zeile ein Java-Objekt
        HerstellerController.selectHersteller();

        for(Hersteller h : Hersteller.hersteller.values()){
            System.out.println("\nHersteller: " + h.getName() + " => " + h.getNumber());
        }

        // Liest die Daten aus der SQL-Tabelle Artikel aus und erstellt für jede Zeile ein Java-Objekt
        ArtikelController.selectArtikel();

        for(Artikel artikel : Artikel.artikel.values()){
            System.out.println("\nArtikel: " + artikel.getBezeichnung() + " => " + artikel.getNummer() + "\nHersteller: " + artikel.getHersteller().getName());
        }

        // Liest die Daten aus der SQL-Tabelle Kunde aus und erstellt für jede Zeile ein Java-Objekt
        KundeController.selectKunde();

        for(Kunde k : Kunde.kunden.values()){
            System.out.println("\nKunde: " + k.getName() + " => " + k.getNummer());
        }

        // Liest die Daten aus der SQL-Tabelle Kunde aus und erstellt für jede Zeile ein Java-Objekt
        AdresseController.selectAdresse();


        for(Adresse adresse : Adresse.adressen.values()){
            System.out.println("\nAdresse:\nKunde: " + adresse.getKunde().getName() + "\nStraße: " + adresse.getStraße() + " " + adresse.getHausnummer()   + "\nOrt: " + adresse.getPlz() + " " + adresse.getOrt());
        }

        // Liest die Daten aus der SQL-Tabelle Kunde aus und erstellt für jede Zeile ein Java-Objekt
        BestellungController.selectBestellung();

        for(Bestellung bestellung : Bestellung.bestellungen.values()){
            System.out.println("\nKunde: " + bestellung.getKunde().getName() + "\nRechnungsadresse: " + bestellung.getRechnungsadresse().getStraße() + " " + bestellung.getRechnungsadresse().getHausnummer() + "\nDatum: " + bestellung.getDatum());
        }

        // Liest die Daten aus der SQL-Tabelle Bestellposition aus und erstellt für jede Zeile ein Java-Objekt
        BestellpositionController.selectBestellposition();

        for(Bestellposition bestellposition : Bestellposition.bestellpositionen.values()){
            System.out.println("Kunde: " + bestellposition.getBestellung().getKunde().getName() + "\nBestellung: " + bestellposition.getArtikel().getBezeichnung() + " x " + bestellposition.getAnzahl() + "\nDatum: " + bestellposition.getBestellung().getDatum());
        }


        Hersteller test = HerstellerController.createHersteller("test");
        System.out.println("Inserted: " + test.getNumber() + " => " + test.getName());


    }

    public static void createDatabase(){
        HerstellerController.selectHersteller();
        ArtikelController.selectArtikel();
        KundeController.selectKunde();
        AdresseController.selectAdresse();
        BestellungController.selectBestellung();
        BestellpositionController.selectBestellposition();
    }

}
