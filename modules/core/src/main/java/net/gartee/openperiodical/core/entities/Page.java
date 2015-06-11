package net.gartee.openperiodical.core.entities;

import net.gartee.openperiodical.core.exceptions.ContentExceedsAvailableSizeException;
import net.gartee.openperiodical.core.identities.ContentId;
import net.gartee.openperiodical.core.identities.PageId;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private PageId id;
    private double size;
    private List<Content> contents = new ArrayList<>();

    public Page(PageId id, double size) {
        this.id = id;
        this.size = size;
    }

    public PageId getId() {
        return id;
    }

    public double getSize() {
        return size;
    }

    public void addContent(Content content) {
        Guard.thatContentIsWithinAvailableSize(this, content);

        contents.add(content);
    }

    public void removeContent(final ContentId contentId) {
        contents.removeIf(c -> c.getId().equals(contentId));
    }

    public double getUnavailableSize() {
        double usedSpace = 0;
        for(Content content : contents) {
            usedSpace += content.getSize();
        }

        return usedSpace;
    }

    public double getAvailableSize() {
        return getSize() - getUnavailableSize();
    }

    private static class Guard {
        private static void thatContentIsWithinAvailableSize(Page page, Content content) {
            if(content.getSize() > page.getAvailableSize())
                throw new ContentExceedsAvailableSizeException(content.getId(), page.getId());
        }
    }
}
