package net.gartee.openperiodical.entities;

import net.gartee.openperiodical.identities.PageId;

public class Page {
    private PageId id;

    public Page(PageId id) {
        this.id = id;
    }

    public PageId getId() {
        return id;
    }
}
