package net.gartee.openperiodical.core.persistence.repositories;

import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.exceptions.EntityDoesNotExistException;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.persistence.entities.NewspaperData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateNewspaperRepository implements NewspaperRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateNewspaperRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Newspaper get(PeriodicalId id) {
        NewspaperData data = (NewspaperData) sessionFactory.getCurrentSession().get(NewspaperData.class, id.getValue());
        if(data ==  null) {
            throw new EntityDoesNotExistException(id);
        }

        Newspaper newspaper = new Newspaper(new PeriodicalId(data.getId()));
        newspaper.setName(data.getName());

        return newspaper;
    }

    public void save(Newspaper newspaper) {
        NewspaperData data = new NewspaperData();
        data.setId(newspaper.getId().getValue());
        data.setName(newspaper.getName());

        sessionFactory.getCurrentSession().merge(data);
    }

    public boolean exists(PeriodicalId id) {
        NewspaperData data = (NewspaperData) sessionFactory.getCurrentSession().get(NewspaperData.class, id.getValue());
        return data != null;
    }
}
