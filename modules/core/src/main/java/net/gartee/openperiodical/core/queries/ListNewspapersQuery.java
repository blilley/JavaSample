package net.gartee.openperiodical.core.queries;

import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;

import java.util.ArrayList;
import java.util.List;

public class ListNewspapersQuery extends Query<ListNewspapersCriteria, List<Newspaper>> {

    private final NewspaperRepository newspaperRepository;

    public ListNewspapersQuery(NewspaperRepository newspaperRepository) {

        this.newspaperRepository = newspaperRepository;
    }

    @Override
    public List<Newspaper> execute(ListNewspapersCriteria criteria) {
        return new ArrayList<>();
    }
}