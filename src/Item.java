import org.jetbrains.annotations.NotNull;

public class Item {
    private String name;
    private Cash price;

    public Item() {
    }

    public Item(@NotNull String name, @NotNull Cash price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public Cash getPrice() {
        return price;
    }

    public void setPrice(@NotNull Cash price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name) && price.equals(item.price);
    }

    @Override
    public String toString() {

        return "Item [" +
                "name='" + name + '\'' +
                ", price=" + price.toString()
                ;
    }
}
