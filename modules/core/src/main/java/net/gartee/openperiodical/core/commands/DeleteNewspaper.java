package net.gartee.openperiodical.core.commands;

import net.gartee.openperiodical.core.identities.PeriodicalId;

public class DeleteNewspaper extends Command {
    private PeriodicalId newspaperId;

    public DeleteNewspaper(PeriodicalId newspaperId) {
        this.newspaperId = newspaperId;
    }

    public PeriodicalId getNewspaperId() {
        return newspaperId;
    }
}