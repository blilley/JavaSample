package net.gartee.openperiodical.core.entities;

import net.gartee.openperiodical.core.exceptions.ContentExceedsAvailableSizeException;
import net.gartee.openperiodical.core.identities.ContentId;
import net.gartee.openperiodical.core.identities.PageId;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private PageId id;
    protected double height;
    protected double width;
    private List<Content> contents = new ArrayList<>();

    public Page(PageId id, double height, double width) {
        this.id = id;
        this.height = height;
        this.width = width;
    }

    public PageId getId() {
        return id;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getSize() {
        return height * width;
    }

    public void addContent(Content content) {
        Guard.thatContentDoesNotExceedAvailableSpace(content, this);
        Guard.thatContentDoesNotExceedHeightOfPage(content, this);
        Guard.thatContentDoesNotExceedWidthOfPage(content, this);

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

    public List<Content> getContents() {
        return contents;
    }

    private static class Guard {
        public static void thatContentDoesNotExceedAvailableSpace(Content content, Page page) {
            if(content.getSize() > page.getAvailableSize()) {
                throw new ContentExceedsAvailableSizeException(content.getId(), page.getId());
            }
        }

        public static void thatContentDoesNotExceedHeightOfPage(Content content, Page page) {
            if(content.getHeight() > page.getHeight()) {
                throw new ContentExceedsAvailableSizeException(content.getId(), page.getId());
            }
        }

        public static void thatContentDoesNotExceedWidthOfPage(Content content, Page page) {
            if(content.getWidth() > page.getWidth()) {
                throw new ContentExceedsAvailableSizeException(content.getId(), page.getId());
            }
        }

        public static void thatContentDoesNotOverlapExistingContent(Content content, Page page) {
            if(!content.isFixedSize()) {
                return;
            }

            List<Content> fixedWidthContent = new ArrayList<>();
            for(Content c : page.getContents()) {
                if(c.isFixedSize()) {
                    fixedWidthContent.add(c);
                }
            }

            for(Content c : fixedWidthContent) {

            }
        }
    }
}
