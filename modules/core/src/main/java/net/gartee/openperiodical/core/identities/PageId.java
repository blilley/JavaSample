package net.gartee.openperiodical.core.identities;

public class PageId {
    private static final String TO_STRING_TEMPLATE = "issue %1 - page %2";

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

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;

        if(!(o instanceof PageId))
            return false;

        PageId pageId = (PageId) o;

        return getIssueId().equals(pageId.getIssueId())
            && getPageNumber() == pageId.getPageNumber();
    }

    @Override
    public String toString() {

        String string = String.format(
                TO_STRING_TEMPLATE,
                getIssueId(),
                getPageNumber());

        return string;
    }
}
