package net.gartee.openperiodical.data.mysql;

import net.gartee.openperiodical.core.dataaccess.NewspaperRepository;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.exceptions.EntityDoesNotExistException;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.data.entities.NewspaperData;
import org.hibernate.Session;

public class MySqlNewspaperRepository implements NewspaperRepository {
    private final Session session;

    public MySqlNewspaperRepository(Session session) {
        this.session = session;
    }

    public Newspaper get(PeriodicalId id) {
        NewspaperData data = (NewspaperData) session.get(NewspaperData.class, id.getValue());
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

        session.saveOrUpdate(data);
    }

    public boolean exists(PeriodicalId id) {
        NewspaperData data = (NewspaperData) session.get(NewspaperData.class, id.getValue());
        return data != null;
    }
}
