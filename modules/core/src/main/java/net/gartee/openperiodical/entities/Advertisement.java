package net.gartee.openperiodical.entities;

import net.gartee.openperiodical.identities.AdvertisementId;

public class Advertisement extends Content {
    private AdvertisementId id;

    public Advertisement(AdvertisementId id) {
        this.id = id;
    }

    public AdvertisementId getId() {
        return id;
    }
}
