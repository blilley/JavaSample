package net.gartee.openperiodical.core.identities;

import java.util.UUID;

public class PeriodicalId {
    private UUID value;

    public PeriodicalId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;

        if(!(o instanceof PeriodicalId))
            return false;

        PeriodicalId periodicalId = (PeriodicalId) o;

        return getValue() == periodicalId.getValue();
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
