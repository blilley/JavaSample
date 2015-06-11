package net.gartee.openperiodical.core.identities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IssueId {
    private static final String TO_STRING_TEMPLATE = "%1 - %2";

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

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;

        if(!(o instanceof IssueId))
            return false;

        IssueId issueId = (IssueId) o;

        return getPeriodicalId().equals(issueId.getPeriodicalId())
            && getPublicationDate().equals(issueId.getPublicationDate());
    }

    @Override
    public String toString() {

        String string = String.format(
                TO_STRING_TEMPLATE,
                getPeriodicalId(),
                new SimpleDateFormat("yyyy-MM-dd").format(getPublicationDate()));

        return string;
    }
}
