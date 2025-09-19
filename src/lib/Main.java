package lib;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // 1. Create library service with standard fine policy (10 Rs/day)
        LibraryService service = new LibraryService(new StandardFinePolicy());

        // 2. Add items
        Book book1 = new Book(1, "Java Basics", "Author A", 200);
        service.addItem(book1);

        // 3. Borrow item using human-readable duration
        BorrowRecord br = service.borrowItem(101, 1, "3 days");
        System.out.println("Borrowed: " + br);

        // 4. Simulate late return (2 days late)
        br.setReturnedDate(br.getBorrowDate().plusDays(5));
        FineRecord fine = service.returnItem(101, 1);
        System.out.println("Returned: " + br);
        System.out.println("Fine: Rs " + fine.getAmount());

        // 5. Audit log
        System.out.println("\nAudit Log:");
        service.getAuditLog().forEach(System.out::println);

        // 6. All fines
        System.out.println("\nAll Fines:");
        service.getFines().forEach(f -> System.out.println(
                "User " + f.getUserId() + " | Item " + f.getItemId() + " | Fine: " + f.getAmount()
        ));
    }
}
