import java.util.HashMap;

public class Kunde {

    public static final HashMap<Integer, Kunde> kunden = new HashMap<>();

    private final int nummer;
    private String name;

    public Kunde(int nummer, String name){
        this.nummer = nummer;
        this.name = name;

        kunden.put(nummer, this);
    }

    public int getNummer(){
        return this.nummer;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }



}
