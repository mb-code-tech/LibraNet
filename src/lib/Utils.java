package lib;

public final class Utils {
    private Utils() {}

    /**
     * Parse duration string into days.
     * Examples:
     *  "7d" → 7
     *  "2w" → 14
     *  "3m" → 90 (approx, 30 days per month)
     *  "1y" → 365
     *  "5"  → 5 (default days if unit missing)
     */
 public static int parseDurationToDays(String input) {
    if (input == null || input.isBlank()) {
        throw new IllegalArgumentException("Duration cannot be empty");
    }

    input = input.trim().toLowerCase();

    // Handle plain number like "5"
    if (input.matches("\\d+")) {
        return Integer.parseInt(input);
    }

    // Split by whitespace
    String[] parts = input.split("\\s+");
    if (parts.length != 2) {
        throw new IllegalArgumentException("Unsupported duration format: " + input);
    }

    int num;
    try {
        num = Integer.parseInt(parts[0]);
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid number in duration: " + input);
    }

    String unit = parts[1]; // e.g., "day", "days", "week", etc.

    return switch (unit) {
        case "d", "day", "days" -> num;
        case "w", "week", "weeks" -> num * 7;
        case "m", "month", "months" -> num * 30;
        case "y", "year", "years" -> num * 365;
        default -> throw new IllegalArgumentException("Unsupported duration unit: " + unit);
    };
}
}

