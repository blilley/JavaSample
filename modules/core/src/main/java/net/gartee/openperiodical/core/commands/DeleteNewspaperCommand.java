package net.gartee.openperiodical.core.commands;

import net.gartee.openperiodical.core.identities.PeriodicalId;

public class DeleteNewspaperCommand extends Command {
    private PeriodicalId newspaperId;

    public DeleteNewspaperCommand(PeriodicalId newspaperId) {
        this.newspaperId = newspaperId;
    }

    public PeriodicalId getNewspaperId() {
        return newspaperId;
    }
}