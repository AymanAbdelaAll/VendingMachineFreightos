import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CashPayment implements Payment {
    private HashMap<Coins,Integer> cashEntered;
    private Currency currency;

    public CashPayment() {
    }

    public CashPayment(@NotNull HashMap<Coins,Integer> cashEntered) {
        this.cashEntered = cashEntered;
    }

    @Override
    public Cash pay(@NotNull Cash price) {
        Integer amountAfterPay=getAmount().getAmount()-price.getAmount();
        return new Cash(amountAfterPay,getCurrency());
    }

    @Override
    public Cash getAmount() {
        Integer amount=0;
        for (Coins coin:cashEntered.keySet()){
            amount+=cashEntered.get(coin)*coin.getCoins();
        }
        return new Cash(amount,getCurrency());
    }

    public HashMap<Coins, Integer> getCashEntered() {
        return cashEntered;
    }

    public void setCashEntered(@NotNull HashMap<Coins,Integer> cashEntered) {
        this.cashEntered = cashEntered;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "CashPayment [" +
                "cashEntered=" + cashEntered.toString() +
                " ]";
    }
}
