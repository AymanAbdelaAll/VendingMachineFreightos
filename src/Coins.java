import java.util.ArrayList;
import java.util.HashMap;

public enum Coins {
    EMPTY(0),TEN(10),
    TWENTY(20),FIFTY(50),
    ONE_DOLLAR(100), TWENTY_DOLLAR(2000),
    FIFTY_DOLLAR(5000)
    ;
    private int coins;
    Coins(int value) {
        coins=value;
    }

    public int getCoins() {
        return coins;
    }
    public HashMap<Coins,Integer> getEmptyCoins(){
        HashMap<Coins,Integer> emptyCoins=new HashMap<Coins,Integer>();
        emptyCoins.put(Coins.FIFTY_DOLLAR,0);
        emptyCoins.put(Coins.TWENTY_DOLLAR,0);
        emptyCoins.put(Coins.ONE_DOLLAR,0);
        emptyCoins.put(Coins.FIFTY,0);
        emptyCoins.put(Coins.TWENTY,0);
        emptyCoins.put(Coins.TEN,0);
        return emptyCoins;
    }
    public ArrayList<Coins> listCoins(){
        ArrayList<Coins> coins=new ArrayList<Coins>(){{
            add(Coins.FIFTY_DOLLAR);
            add(Coins.TWENTY_DOLLAR);
            add(Coins.ONE_DOLLAR);
            add(Coins.FIFTY);
            add(Coins.TWENTY);
            add(Coins.TEN);
        }};
        return coins;
    }

    @Override
    public String toString() {
        return "{10c ,20c ,50c ,1$ ,20$ ,50$}";
    }
}
