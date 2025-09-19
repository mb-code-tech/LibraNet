package lib;

public class Book extends LibraryItem {
    private final int pageCount;

    public Book(int id, String title, String author, int pageCount) {
        super(id, title, author);
        if (pageCount < 0) throw new IllegalArgumentException("pageCount must be >= 0");
        this.pageCount = pageCount;
    }

    public int getPageCount() { return pageCount; }

    @Override
    public String getType() { return "Book"; }
}
