import java.math.BigDecimal;

public class Onlineshop {


    public static void main(String[] args) {

        getDatabase();
        /*
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

        for(Bestellposition bestellposition : Bestellposition.bestellpositionen){
            System.out.println("Kunde: " + bestellposition.getBestellung().getKunde().getName() + "\nBestellung: " + bestellposition.getArtikel().getBezeichnung() + " x " + bestellposition.getAnzahl() + "\nDatum: " + bestellposition.getBestellung().getDatum());
        }

        // ******* Verändern der Datenbank *******

        Hersteller testHersteller = HerstellerController.createHersteller("test");
        System.out.println("Inserted: " + testHersteller.getNumber() + " => " + testHersteller.getName());

        Artikel testArtikel = ArtikelController.createArtikel("testArtikel", BigDecimal.valueOf(50.0), testHersteller);
        if(testArtikel != null){

        System.out.println("Inserted: " + testArtikel.getBezeichnung() + " " + testArtikel.getPreis() +  " " + " von: " + testArtikel.getHersteller().getName());
        }

        testHersteller.setName("Der Tester");
        testArtikel.setBezeichnung("Hammer dingen");
        testArtikel.setPreis(BigDecimal.valueOf(3.50));

        KundeController.createKunde("Alfie");

        Hersteller alfieHersteller = HerstellerController.createHersteller("AlfieGmbH");

        Artikel alfieArtikel = ArtikelController.createArtikel("Der Alfie 3000", BigDecimal.valueOf(450.49), alfieHersteller);

        Kunde rudi = KundeController.createKunde("Rudi");
        Adresse rudiAdresse1 = AdresseController.createAdresse("teststraße", 12345, "Castrop testen", 4, rudi);

        Adresse rudiAdresse2 = AdresseController.createAdresse("straßentest", 12345, "Castrop testen", 11, rudi);


        Bestellung rudisBestellung = BestellungController.createBestellung(rudi, rudiAdresse1, rudiAdresse2, "2024-11-15 09:55:35");

        Bestellposition rudisBestellposition = BestellpositionController.createBestellposition(rudisBestellung, alfieArtikel, 4);

        System.out.println("Bestelldatum: " + rudisBestellposition.getBestellung().getDatum() + " Artikel: " + rudisBestellposition.getArtikel().getBezeichnung() + " Anzahl: " + rudisBestellposition.getAnzahl());

        Hersteller updateHersteller = HerstellerController.createHersteller("UpdateGMBH");

        Artikel updateArtikel = ArtikelController.createArtikel("Das update 3000", BigDecimal.valueOf(450.49), updateHersteller);

        Kunde updateKunde = KundeController.createKunde("UPDATE Kunde");
        Adresse updateAdresse1 = AdresseController.createAdresse("updatestraße", 54321, "Castrop update", 4, updateKunde);

        Adresse updateAdresse2 = AdresseController.createAdresse("straßenupdate", 12345, "Castropupdate", 11, updateKunde);


        Bestellung updateBestellung = BestellungController.createBestellung(updateKunde, updateAdresse1, updateAdresse2, "2024-11-15 09:55:35");

        Bestellposition updateBestellposition = BestellpositionController.createBestellposition(updateBestellung, updateArtikel, 2);

        AdresseController.updateAdresse(updateAdresse2, "plz", 54321);
        BestellungController.updateBestellung(updateBestellung, "datum", "2023-06-15 09:55:35");

        BestellpositionController.updateBestellposition(updateBestellposition, "anzahl", 6);

        updateKunde.setName("Kundeupdate");

        updateAdresse1.setOrt("Düse");
        updateAdresse1.setPlz(67891);
        updateAdresse1.setStraße("Düse-Street");
        updateAdresse1.setHausnummer(24);

        updateBestellung.setDatum("2024-12-31 23:59:59");

        updateBestellposition.setAnzahl(9);

        // Testen der View selectAll
        System.out.println(SQLController.selectAll());

        int neueID = SQLController.insertArtikel("Autogramm von Alfie", BigDecimal.valueOf(643.89), 3);
        System.out.println("Ergebnis : " + neueID);

        ArtikelController.selectArtikel();

        for(Artikel tmpArtikel : Artikel.artikel.values()) {
            if(tmpArtikel.getNummer() == 15) {
                tmpArtikel.setArtikelPreis(BigDecimal.valueOf(38.44));
            }
        }

        int result = SQLController.updateArtikelPreis(4, BigDecimal.valueOf(49.99));
        System.out.println("Result : " + result);
        // Testen der View selectKundenDaten
        System.out.println(SQLController.selectKundenDaten());

        // Testen Java Methode mit eigebundene SQL Prozedure
        Hersteller CreateGmbH = HerstellerController.createHersteller("Create GmbH");

        ArtikelController.createArtikel("DerHammer", BigDecimal.valueOf(10.99), CreateGmbH);
        ArtikelController.selectArtikel();
        for(Artikel tmpArtikel : Artikel.artikel.values()) {

            System.out.println("Artikel: " + tmpArtikel.getBezeichnung());
            System.out.println("Preis: " + tmpArtikel.getPreis());
            System.out.println("Hertseller: " + tmpArtikel.getHersteller().getName());
            System.out.println("");

        }

        // Testen Java Methode mit eigebundene SQL Prozedure
        Kunde Alfie = KundeController.createKunde("Alfie");

        AdresseController.createAdresse("Fahrdamm", 67891, "Düse", 88, Alfie);
        AdresseController.selectAdresse();
        for(Adresse tmpAdresse : Adresse.adressen.values()) {

            System.out.println("Name: " + tmpAdresse.getKunde().getName());
            System.out.println("Straße: " + tmpAdresse.getStraße());
            System.out.println("HausNr.: " + tmpAdresse.getHausnummer());
            System.out.println("PLZ: " + tmpAdresse.getPlz());
            System.out.println("Ort: " + tmpAdresse.getOrt());
            System.out.println("");

        }
         */





    }

    public static void getDatabase(){
        HerstellerController.selectHersteller();
        ArtikelController.selectArtikel();
        KundeController.selectKunde();
        AdresseController.selectAdresse();
        BestellungController.selectBestellung();
        BestellpositionController.selectBestellposition();
    }

}
