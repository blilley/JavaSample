package net.gartee.openperiodical.core.exceptions;

import net.gartee.openperiodical.core.identities.PeriodicalId;

public class PeriodicalAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Cannot insert periodical (%s). That ID is already in use.";

    public PeriodicalAlreadyExistsException(PeriodicalId periodicalId) {
        super(buildMessage(periodicalId));
    }

    private static String buildMessage(PeriodicalId periodicalId) {
        return String.format(MESSAGE_TEMPLATE, periodicalId);
    }
}