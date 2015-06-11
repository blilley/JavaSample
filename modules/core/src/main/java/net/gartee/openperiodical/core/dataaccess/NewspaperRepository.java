package net.gartee.openperiodical.core.dataaccess;

import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;

public interface NewspaperRepository {
    void save(Newspaper newspaper);
    boolean exists(PeriodicalId id);
}
