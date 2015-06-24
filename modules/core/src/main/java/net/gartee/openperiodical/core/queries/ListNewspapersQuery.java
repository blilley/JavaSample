package net.gartee.openperiodical.core.queries;

import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ListNewspapersQuery extends Query<ListNewspapersCriteria, List<Newspaper>> {
    private final NewspaperRepository newspaperRepository;

    @Autowired
    public ListNewspapersQuery(NewspaperRepository newspaperRepository) {
        this.newspaperRepository = newspaperRepository;
    }

    @Override
    public List<Newspaper> execute(ListNewspapersCriteria criteria) {
        return newspaperRepository.getAll();
    }
}