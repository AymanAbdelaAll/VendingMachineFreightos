import org.jetbrains.annotations.NotNull;

public class Order {
    private Item item;
    private Payment payment;

    public Order() {
    }

    public Order(@NotNull Item item, @NotNull Payment payment) {
        this.item = item;
        this.payment = payment;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(@NotNull Item item) {
        this.item = item;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(@NotNull Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return item.equals(order.item) && payment.equals(order.payment);
    }

    @Override
    public String toString() {
        return "Order{" +
                item.toString() +
                payment.toString() +
                '}';
    }
}
