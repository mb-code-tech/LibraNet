package lib;

public class EMagazine extends LibraryItem {
    private final String issue;
    private boolean archived = false;

    public EMagazine(int id, String title, String author, String issue) {
        super(id, title, author);
        if (issue == null) throw new IllegalArgumentException("issue required");
        this.issue = issue;
    }

    public String getIssue() { return issue; }

    /** Archives the issue; returns true if archived now (idempotent). */
    public boolean archiveIssue() {
        if (!archived) {
            archived = true;
            System.out.println("Archiving e-magazine issue: " + issue);
            return true;
        }
        return false;
    }

    public boolean isArchived() { return archived; }

    @Override
    public String getType() { return "EMagazine"; }
}
