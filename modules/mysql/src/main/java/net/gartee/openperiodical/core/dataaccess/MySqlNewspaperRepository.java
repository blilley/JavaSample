package net.gartee.openperiodical.core.dataaccess;

import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;

public class MySqlNewspaperRepository implements NewspaperRepository {
    public void save(Newspaper newspaper) {

    }

    public boolean exists(PeriodicalId id) {
        return false;
    }
}
