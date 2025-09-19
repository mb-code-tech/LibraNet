package lib;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a borrow record for a library item.
 */
public class BorrowRecord {

    private final int userId;
    private final int itemId;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private LocalDate returnedDate; // nullable until returned

    // Constructor for duration in days
    public BorrowRecord(int userId, int itemId, int borrowedDays) {
        this.userId = userId;
        this.itemId = itemId;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(borrowedDays);
    }

    // Constructor with explicit dates
    public BorrowRecord(int userId, int itemId, LocalDate borrowDate, LocalDate dueDate) {
        this.userId = userId;
        this.itemId = itemId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public int getUserId() { return userId; }
    public int getItemId() { return itemId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }

    /** Nullable returnedDate, may be null if not yet returned */
    public LocalDate getReturnedDate() { return returnedDate; }

    public void setReturnedDate(LocalDate returnedDate) { this.returnedDate = returnedDate; }

    /** Number of overdue days (0 if not overdue) computed against returnedDate or today */
    public long getOverdueDays() {
        LocalDate when = (returnedDate == null) ? LocalDate.now() : returnedDate;
        long diff = ChronoUnit.DAYS.between(dueDate, when);
        return Math.max(0, diff);
    }

    @Override
    public String toString() {
        return String.format("User %d borrowed itemId=%d on %s, due %s, returned %s",
                userId, itemId, borrowDate, dueDate,
                returnedDate == null ? "N/A" : returnedDate.toString());
    }
}
