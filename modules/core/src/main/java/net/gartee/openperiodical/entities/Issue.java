package net.gartee.openperiodical.entities;

import net.gartee.openperiodical.identities.IssueId;

public class Issue {
    private IssueId id;

    public Issue(IssueId id) {
        this.id = id;
    }

    public IssueId getId() {
        return id;
    }
}
