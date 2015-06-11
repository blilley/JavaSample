package net.gartee.openperiodical.core.identities;

public class PeriodicalId {
    private int id;

    public PeriodicalId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;

        if(!(o instanceof PeriodicalId))
            return false;

        PeriodicalId periodicalId = (PeriodicalId) o;

        return getId() == periodicalId.getId();
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }
}
