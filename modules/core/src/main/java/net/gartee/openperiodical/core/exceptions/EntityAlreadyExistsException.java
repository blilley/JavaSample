package net.gartee.openperiodical.core.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Entity already exists (%s).";

    public EntityAlreadyExistsException(Object id) {
        super(buildMessage(id));
    }

    private static String buildMessage(Object id) {
        return String.format(MESSAGE_TEMPLATE, id);
    }
}
