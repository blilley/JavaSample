package net.gartee.openperiodical.core.entities;

import net.gartee.openperiodical.core.identities.ContentId;

public class Advertisement extends Content {

    public Advertisement(ContentId id) {
        super(id);
    }

    @Override
    public boolean isFixedSize() {
        return true;
    }
}