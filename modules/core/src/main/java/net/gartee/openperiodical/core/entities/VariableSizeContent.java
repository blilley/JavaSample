package net.gartee.openperiodical.core.entities;

import net.gartee.openperiodical.core.identities.ContentId;

public class VariableSizeContent extends Content {

    public VariableSizeContent(ContentId id) {
        super(id);
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}