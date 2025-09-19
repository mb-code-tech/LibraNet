package lib;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Service to manage library items, borrowing, returning, and fine calculation.
 */
public class LibraryService {

    private final Map<Integer, LibraryItem> items = new ConcurrentHashMap<>();
    private final Map<Integer, BorrowRecord> activeBorrows = new ConcurrentHashMap<>();
    private final List<FineRecord> fines = Collections.synchronizedList(new ArrayList<>());
    private final List<String> auditLog = Collections.synchronizedList(new ArrayList<>());
    private final FinePolicy finePolicy;

    public LibraryService(FinePolicy finePolicy) {
        this.finePolicy = Objects.requireNonNull(finePolicy, "FinePolicy cannot be null");
    }

    /** Add a new item to the library */
    public void addItem(LibraryItem item) {
        Objects.requireNonNull(item, "LibraryItem cannot be null");
        items.put(item.getId(), item);
        auditLog.add("Added: " + item);
    }

    /** Borrow an item by ID for a given duration string */
    public BorrowRecord borrowItem(int userId, int itemId, String durationStr) {
        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalArgumentException("Item not found: " + itemId);

        synchronized (item) {
            if (!item.isAvailable()) throw new IllegalStateException("Item is not available: " + itemId);

            int days = Utils.parseDurationToDays(durationStr); // Updated parser
            LocalDate borrowDate = LocalDate.now();
            LocalDate dueDate = borrowDate.plusDays(days);

            BorrowRecord record = new BorrowRecord(userId, itemId, borrowDate, dueDate);
            if (!item.borrow())
                throw new IllegalStateException("Concurrent borrow failure for: " + itemId);

            activeBorrows.put(itemId, record);
            auditLog.add(String.format("User %d borrowed: %s for %d days", userId, item, days));
            return record;
        }
    }

    /** Overloaded borrow using LibraryItem directly */
    public BorrowRecord borrowItem(int userId, LibraryItem item, String durationStr) {
        Objects.requireNonNull(item, "LibraryItem cannot be null");
        return borrowItem(userId, item.getId(), durationStr);
    }

    /** Return an item and compute fine */
    public FineRecord returnItem(int userId, int itemId) {
        BorrowRecord record = activeBorrows.get(itemId);
        if (record == null) throw new IllegalArgumentException("No active borrow found for item: " + itemId);
        if (record.getUserId() != userId) throw new IllegalArgumentException("This user did not borrow the item");

        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalStateException("Item record disappeared: " + itemId);

        synchronized (item) {
            LocalDate returnedDate = record.getReturnedDate();
            if (returnedDate == null) {
                returnedDate = LocalDate.now();
                record.setReturnedDate(returnedDate);
            }

            BigDecimal fineAmount = finePolicy.computeFine(record);

            item.returned();
            activeBorrows.remove(itemId);

            FineRecord fr = new FineRecord(userId, itemId, fineAmount, returnedDate);
            fines.add(fr);

            auditLog.add(String.format("User %d returned: %s | Fine: %s", userId, item, fineAmount));
            return fr;
        }
    }

    /** Overloaded return using LibraryItem directly */
    public FineRecord returnItem(int userId, LibraryItem item) {
        Objects.requireNonNull(item, "LibraryItem cannot be null");
        return returnItem(userId, item.getId());
    }

    /** Search items by type */
    public List<LibraryItem> searchByType(Class<? extends LibraryItem> type) {
        return items.values().stream().filter(type::isInstance).collect(Collectors.toList());
    }

    /** List all items */
    public Collection<LibraryItem> listAllItems() {
        return Collections.unmodifiableCollection(items.values());
    }

    /** Get audit log */
    public List<String> getAuditLog() {
        return new ArrayList<>(auditLog);
    }

    /** Get all fines */
    public List<FineRecord> getFines() {
        synchronized (fines) {
            return new ArrayList<>(fines);
        }
    }
}
