package net.gartee.openperiodical.core.entities;

import net.gartee.openperiodical.core.identities.PeriodicalId;

public abstract class Periodical {
    private PeriodicalId id;
    private String name;

    public Periodical(PeriodicalId id) {
        this.id = id;
    }

    public PeriodicalId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
