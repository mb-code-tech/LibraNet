package lib;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Objects;

public abstract class LibraryItem {
    private final int id;
    private final String title;
    private final String author;
    private final AtomicBoolean available = new AtomicBoolean(true);

    public LibraryItem(int id, String title, String author) {
        if (title == null || author == null) throw new IllegalArgumentException("title/author required");
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    public boolean isAvailable() { return available.get(); }

    /** Returns true if borrow succeeded, false if already borrowed */
    public boolean borrow() { return available.compareAndSet(true, false); }

    public void returned() { available.set(true); }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("[%s] %s by %s (id=%d)", getType(), title, author, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryItem)) return false;
        LibraryItem that = (LibraryItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
