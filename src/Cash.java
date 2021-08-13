import org.jetbrains.annotations.NotNull;

public class Cash {
    private Integer amount;
    private Currency currency;

    public Cash() {
    }

    public Cash(@NotNull Integer amount, @NotNull Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(@NotNull Integer amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(@NotNull Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return " " + amount +""+ currency.getSymbol() + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cash cash = (Cash) o;
        return amount.equals(cash.amount) && currency == cash.currency;
    }

}
