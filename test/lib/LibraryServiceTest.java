package lib;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.math.BigDecimal;

public class LibraryServiceTest {

    private LibraryService service;

    @BeforeEach
    void setup() {
        service = new LibraryService(new StandardFinePolicy()); // uses default Rs 10/day
    }

    @Test
    void testBorrowAndReturnWithFine() {
        Book book = new Book(1, "Java Basics", "Author A", 200);
        service.addItem(book);

        BorrowRecord br = service.borrowItem(101, 1, "3"); // borrow for 3 days
        assertNotNull(br);

        // simulate a late return: set returnedDate to borrowDate + 5 days (2 days late)
        br.setReturnedDate(br.getBorrowDate().plusDays(5));

        FineRecord fr = service.returnItem(101, 1); // will compute fine based on returnedDate
        assertEquals(20, fr.getAmount().intValue()); // 2 days late * Rs 10/day
    }

    @Test
    void testReturnOnTimeNoFine() {
        Book book = new Book(2, "Clean Code", "Author B", 300);
        service.addItem(book);

        BorrowRecord br = service.borrowItem(102, 2, "5"); // borrow for 5 days
        br.setReturnedDate(br.getBorrowDate().plusDays(5)); // returned on time

        FineRecord fr = service.returnItem(102, 2);
        assertEquals(0, fr.getAmount().intValue());
    }

    @Test
    void testItemNotFound() {
        assertThrows(IllegalArgumentException.class, () -> service.borrowItem(201, 99, "2"));
    }
}
