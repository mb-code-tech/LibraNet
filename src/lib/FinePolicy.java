package lib;

import java.math.BigDecimal;

public interface FinePolicy {
    /** Compute fine for a borrow record. */
    BigDecimal computeFine(BorrowRecord record);
}
