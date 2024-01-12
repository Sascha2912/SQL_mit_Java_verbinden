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
    }

}
