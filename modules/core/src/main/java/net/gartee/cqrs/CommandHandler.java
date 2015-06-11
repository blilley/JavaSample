package net.gartee.cqrs;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}
