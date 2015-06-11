package net.gartee.openperiodical.core.entities;

import net.gartee.openperiodical.core.identities.IssueId;
import net.gartee.openperiodical.core.identities.PageId;

import java.util.ArrayList;
import java.util.List;

public class Issue {
    private IssueId id;
    private List<Page> pages = new ArrayList<Page>();

    public Issue(IssueId id) {
        this.id = id;
    }

    public IssueId getId() {
        return id;
    }

    public void addPage(Page page) {
        pages.add(page);
    }

    public void removePage(PageId pageId) {
        pages.removeIf(c -> c.getId().equals(pageId));
    }

    public int getPageCount() {
        return pages.size();
    }
}
