package net.gartee.openperiodical.core.exceptions;

import net.gartee.openperiodical.core.identities.ContentId;
import net.gartee.openperiodical.core.identities.PageId;

public class ContentExceedsAvailableSizeException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Unable to add content (%1) to page (%2). Available size exceeded.";

    public ContentExceedsAvailableSizeException(ContentId contentId, PageId pageId) {
        super(buildMessage(contentId, pageId));
    }

    private static String buildMessage(ContentId contentId, PageId pageId) {
        return String.format(MESSAGE_TEMPLATE, contentId, pageId);
    }
}