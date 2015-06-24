package net.gartee.openperiodical.core.persistence.repositories;

import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;

import java.util.List;

public interface NewspaperRepository {
    Newspaper get(PeriodicalId id);
    List<Newspaper> getAll();
    List<Newspaper> fetchByNameContains(String nameContains);
    void save(Newspaper newspaper);
    boolean exists(PeriodicalId id);
}