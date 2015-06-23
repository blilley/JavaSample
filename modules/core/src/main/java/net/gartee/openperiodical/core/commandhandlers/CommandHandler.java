package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.openperiodical.core.commands.Command;

public abstract class CommandHandler<T extends Command> {
    public abstract void handle(T command);
}