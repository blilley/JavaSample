package net.gartee.openperiodical.core.builders;

import net.gartee.openperiodical.core.entities.Page;
import net.gartee.openperiodical.core.identities.IssueId;
import net.gartee.openperiodical.core.identities.PageId;
import net.gartee.openperiodical.core.identities.PeriodicalId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PageBuilder {
    private UUID uuid;
    private String dateString;
    private int pageNumber;
    private double height;
    private double width;

    public PageBuilder() {

    }

    public static PageBuilder create() {
        return new PageBuilder();
    }

    public PageBuilder withPeriodicalId(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public PageBuilder withPublicationDate(String dateString) {
        this.dateString = dateString;
        return this;
    }

    public PageBuilder withPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public PageBuilder withHeight(double height) {
        this.height = height;
        return this;
    }

    public PageBuilder withWidth(double width) {
        this.width = width;
        return this;
    }

    public Page build() {
        return new Page(
            new PageId(
                new IssueId(
                    new PeriodicalId(uuid),
                    buildDate(dateString)),
                pageNumber),
            height,
            width
        );
    }

    private Date buildDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(dateString);
        }
        catch (ParseException ex) {
            return null;
        }
    }
}
