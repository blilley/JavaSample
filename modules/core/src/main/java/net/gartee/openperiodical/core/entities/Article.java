package net.gartee.openperiodical.core.entities;

import net.gartee.openperiodical.core.identities.ContentId;

public class Article extends Content {
    public Article(ContentId id) {
        super(id);
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public boolean isFixedSize() {
        return true;
    }
}