package net.gartee.openperiodical.core.commands;

import net.gartee.openperiodical.core.identities.PeriodicalId;

public class RenameNewspaper extends Command {
    private PeriodicalId newspaperId;
    private String newName;

    public RenameNewspaper(PeriodicalId newspaperId, String newName) {
        this.newspaperId = newspaperId;
        this.newName = newName;
    }

    public PeriodicalId getNewspaperId() {
        return newspaperId;
    }

    public String getNewName() {
        return newName;
    }
}
