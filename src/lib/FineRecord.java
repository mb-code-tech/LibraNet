package lib;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FineRecord {
    private final int userId;
    private final int itemId;
    private final BigDecimal amount;
    private final LocalDate date;

    public FineRecord(int userId, int itemId, BigDecimal amount, LocalDate date) {
        this.userId = userId;
        this.itemId = itemId;
        this.amount = amount;
        this.date = date;
    }

    public BigDecimal getAmount() { return amount; }
    public int getUserId() { return userId; }
    public int getItemId() { return itemId; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return String.format("User %d returned item %d | Fine: %s (on %s)", userId, itemId, amount, date);
    }
}
