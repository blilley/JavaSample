package net.gartee.openperiodical.core.dataaccess;

import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;

public interface NewspaperRepository {
    Newspaper get(PeriodicalId id);

    void save(Newspaper newspaper);
    boolean exists(PeriodicalId id);
}
