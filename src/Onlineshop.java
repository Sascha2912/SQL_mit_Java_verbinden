import java.math.BigDecimal;

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

        for(Bestellposition bestellposition : Bestellposition.bestellpositionen){
            System.out.println("Kunde: " + bestellposition.getBestellung().getKunde().getName() + "\nBestellung: " + bestellposition.getArtikel().getBezeichnung() + " x " + bestellposition.getAnzahl() + "\nDatum: " + bestellposition.getBestellung().getDatum());
        }

        // ******* Verändern der Datenbank *******

        /*
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
        */

        Hersteller updateHersteller = HerstellerController.createHersteller("UpdateGMBH");

        Artikel updateArtikel = ArtikelController.createArtikel("Das update 3000", BigDecimal.valueOf(450.49), updateHersteller);

        Kunde updateKunde = KundeController.createKunde("UPDATE Kunde");
        Adresse updateAdresse1 = AdresseController.createAdresse("updatestraße", 54321, "Castrop update", 4, updateKunde);

        Adresse updateAdresse2 = AdresseController.createAdresse("straßenupdate", 12345, "Castropupdate", 11, updateKunde);


        Bestellung updateBestellung = BestellungController.createBestellung(updateKunde, updateAdresse1, updateAdresse2, "2024-11-15 09:55:35");

        Bestellposition updateBestellposition = BestellpositionController.createBestellposition(updateBestellung, updateArtikel, 2);

        //AdresseController.updateAdresse(updateAdresse2, "plz", 54321);





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
