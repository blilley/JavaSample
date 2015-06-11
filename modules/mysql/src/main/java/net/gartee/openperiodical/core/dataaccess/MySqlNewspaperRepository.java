package net.gartee.openperiodical.core.dataaccess;

import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;

public class MySqlNewspaperRepository implements NewspaperRepository {
    @Override
    public void save(Newspaper newspaper) {

    }

    @Override
    public boolean exists(PeriodicalId id) {
        return false;
    }
}
