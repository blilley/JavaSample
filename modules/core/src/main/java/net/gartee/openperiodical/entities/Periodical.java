package net.gartee.openperiodical.entities;

import net.gartee.openperiodical.identities.PeriodicalId;

public abstract class Periodical {
    private PeriodicalId id;

    public Periodical(PeriodicalId id) {
        this.id = id;
    }

    public PeriodicalId getId() {
        return id;
    }
}
