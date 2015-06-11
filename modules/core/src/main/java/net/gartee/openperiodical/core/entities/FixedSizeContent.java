package net.gartee.openperiodical.core.entities;

import net.gartee.openperiodical.core.identities.ContentId;

public class FixedSizeContent extends Content {

    public FixedSizeContent(ContentId id, double height, double width) {
        super(id);
        this.height = height;
        this.width = width;
    }
}
