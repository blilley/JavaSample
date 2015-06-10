package net.gartee.openperiodical.identities;

public class PageId {
    private IssueId issueId;
    private int pageNumber;

    public PageId(IssueId issueId, int pageNumber) {
        this.issueId = issueId;
        this.pageNumber = pageNumber;
    }

    public IssueId getIssueId() {
        return issueId;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
