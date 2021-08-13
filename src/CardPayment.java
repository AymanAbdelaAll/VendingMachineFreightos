import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CardPayment implements Payment{
    private Cash cardBalanced;

    public CardPayment() {
        Random random = new Random();
        int balanced=5000;       //50$
        this.cardBalanced=new Cash(balanced,Currency.CENT);
    }


    @Override
    public Cash pay(@NotNull Cash price) {
        cardBalanced.setAmount(cardBalanced.getAmount()-price.getAmount());
        return cardBalanced;
    }

    @Override
    public Cash getAmount() {
        return cardBalanced;
    }

    public Cash getCardBalanced() {
        return cardBalanced;
    }

    public void setCardBalanced(@NotNull Cash cardBalanced) {
        this.cardBalanced = cardBalanced;
    }

    @Override
    public String toString() {
        return "CardPayment{" +
                "cardBalanced=" + cardBalanced.toString() +
                '}';
    }
}
