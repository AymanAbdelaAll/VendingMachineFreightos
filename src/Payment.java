import org.jetbrains.annotations.NotNull;

public interface Payment {
    public Cash pay(@NotNull Cash price);
    public Cash getAmount();
}
