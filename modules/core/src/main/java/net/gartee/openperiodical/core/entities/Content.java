package net.gartee.openperiodical.core.entities;

import net.gartee.openperiodical.core.identities.ContentId;

public abstract class Content {
    private ContentId id;
    protected double height;
    protected double width;

    public Content(ContentId id) {
        this.id = id;
    }

    public ContentId getId() {
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

    public abstract boolean isFixedSize();
}