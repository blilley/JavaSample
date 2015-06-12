package net.gartee.openperiodical.core.identities;

public class PeriodicalId {
    private int value;

    public PeriodicalId(int value) {
        this.value = value;
    }

    public int getValue() {
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
