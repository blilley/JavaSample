package net.gartee.openperiodical.core.commands;

import net.gartee.openperiodical.core.identities.PeriodicalId;

public class CreateNewspaperCommand extends Command {
    private PeriodicalId newspaperId;
    private String newspaperName;

    public CreateNewspaperCommand(PeriodicalId newspaperId, String newspaperName) {
        this.newspaperId = newspaperId;
        this.newspaperName = newspaperName;
    }

    public PeriodicalId getNewspaperId() {
        return newspaperId;
    }

    public String getNewspaperName() {
        return newspaperName;
    }
}