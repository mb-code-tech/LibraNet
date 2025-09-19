package lib;

import java.math.BigDecimal;

public class StandardFinePolicy implements FinePolicy {
    private final BigDecimal ratePerDay;

    public StandardFinePolicy() { this(new BigDecimal("10")); } // default 10 rs/day
    public StandardFinePolicy(BigDecimal ratePerDay) {
        if (ratePerDay == null || ratePerDay.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("ratePerDay must be non-negative");
        this.ratePerDay = ratePerDay;
    }

    @Override
    public java.math.BigDecimal computeFine(BorrowRecord record) {
        long daysOverdue = record.getOverdueDays();
        return ratePerDay.multiply(BigDecimal.valueOf(daysOverdue));
    }
}
