package lib;

public class Audiobook extends LibraryItem implements Playable {
    private final int durationMinutes;

    public Audiobook(int id, String title, String author, int durationMinutes) {
        super(id, title, author);
        if (durationMinutes < 0) throw new IllegalArgumentException("durationMinutes must be >= 0");
        this.durationMinutes = durationMinutes;
    }

    @Override
    public void play() {
        // simple behavior: print; real system would integrate with player
        System.out.println("Playing audiobook: " + getTitle() + " (" + durationMinutes + " mins)");
    }

    @Override
    public int getDurationMinutes() { return durationMinutes; }

    @Override
    public String getType() { return "Audiobook"; }
}
