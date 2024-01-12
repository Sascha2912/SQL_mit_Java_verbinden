import java.util.HashMap;

public class Hersteller {

    public static final HashMap<Integer, Hersteller> hersteller = new HashMap<>();

    private int nummer;
    private String name;

    Hersteller(int nummer, String name){
        this.nummer = nummer;
        this.name = name;

        hersteller.put(nummer, this);
    }

    public int getNumber(){
        return this.nummer;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        // TODO: Datenbank bescheid geben !!!
        this.name = name;
    }


}
