package net.gartee.openperiodical.identities;

import java.util.Date;

public class IssueId {
    private PeriodicalId periodicalId;
    private Date publicationDate;

    public IssueId(PeriodicalId periodicalId, Date publicationDate) {
        this.periodicalId = periodicalId;
        this.publicationDate = publicationDate;
    }

    public PeriodicalId getPeriodicalId() {
        return periodicalId;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }
}
