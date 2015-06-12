package net.gartee.openperiodical.core.exceptions;

public class EntityDoesNotExistException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Entity does not exist (%s).";

    public EntityDoesNotExistException(Object id) {
        super(buildMessage(id));
    }

    private static String buildMessage(Object id) {
        return String.format(MESSAGE_TEMPLATE, id);
    }
}
