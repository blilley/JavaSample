package net.gartee.openperiodical.core.persistence.repositories;

import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.exceptions.EntityDoesNotExistException;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.persistence.entities.NewspaperData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HibernateNewspaperRepositoryTest {

    private static final int EXISTING_NEWSPAPER_1_ID = 1;
    private static final String EXISTING_NEWSPAPER_1_NAME = "Newspaper 1";
    private static final String UPDATED_NEWSPAPER_1_NAME = "Updated Newspaper 1";

    private static final int EXISTING_NEWSPAPER_2_ID = 2;
    private static final String EXISTING_NEWSPAPER_2_NAME = "Newspaper 2";

    private static final int NEW_NEWSPAPER_ID = 3;
    private static final String NEW_NEWSPAPER_NAME = "New Newspaper";

    private SessionFactory sessionFactory;
    private Session session;

    @BeforeClass
    public static void setupDatabase() {
        SeedDatabase(HibernateUtil.getSessionFactory().getCurrentSession());
    }

    @Before
    public void setupSession() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
    }

    @Test
    public void get_WithExistingId_ReturnsNewspaper() {
        HibernateNewspaperRepository repository = new HibernateNewspaperRepository(sessionFactory);
        PeriodicalId newspaperId = new PeriodicalId(EXISTING_NEWSPAPER_1_ID);

        Newspaper newspaper = repository.get(newspaperId);

        assertThat(newspaper.getId(), is(newspaperId));
        assertThat(newspaper.getName(), is(EXISTING_NEWSPAPER_1_NAME));
    }

    @Test(expected = EntityDoesNotExistException.class)
    public void get_WithNonExistentId_ThrowsException() {
        HibernateNewspaperRepository repository = new HibernateNewspaperRepository(sessionFactory);
        PeriodicalId newspaperId = new PeriodicalId(NEW_NEWSPAPER_ID);

        repository.get(newspaperId);
    }

    @Test
    public void getAll_ReturnsAllNewspapers() {
        HibernateNewspaperRepository repository = new HibernateNewspaperRepository(sessionFactory);
        List<Newspaper> results = repository.getAll();

        assertThat(results.size(), is(2));

        assertThat(results.get(0).getId().getValue(), is(EXISTING_NEWSPAPER_1_ID));
        assertThat(results.get(0).getName(), is(EXISTING_NEWSPAPER_1_NAME));

        assertThat(results.get(1).getId().getValue(), is(EXISTING_NEWSPAPER_2_ID));
        assertThat(results.get(1).getName(), is(EXISTING_NEWSPAPER_2_NAME));
    }

    @Test
    public void exists_WhenIdExists_ReturnsTrue() {
        HibernateNewspaperRepository repository = new HibernateNewspaperRepository(sessionFactory);
        PeriodicalId newspaperId = new PeriodicalId(EXISTING_NEWSPAPER_1_ID);

        assertTrue(repository.exists(newspaperId));
    }

    @Test
    public void exists_WhenIdDoesNotExist_ReturnsFalse() {
        HibernateNewspaperRepository repository = new HibernateNewspaperRepository(sessionFactory);
        PeriodicalId newspaperId = new PeriodicalId(NEW_NEWSPAPER_ID);

        assertFalse(repository.exists(newspaperId));
    }

    @Test
    public void save_WithExistingNewspaper_UpdatesRecord() {
        HibernateNewspaperRepository repository = new HibernateNewspaperRepository(sessionFactory);
        Newspaper newsPaper = new Newspaper(new PeriodicalId(EXISTING_NEWSPAPER_1_ID));
        newsPaper.setName(UPDATED_NEWSPAPER_1_NAME);

        repository.save(newsPaper);

        NewspaperData newsPaperData = (NewspaperData) session.get(NewspaperData.class, EXISTING_NEWSPAPER_1_ID);
        assertThat(newsPaperData.getId(), is(EXISTING_NEWSPAPER_1_ID));
        assertThat(newsPaperData.getName(), is(UPDATED_NEWSPAPER_1_NAME));
    }

    @Test
    public void save_WithNewNewspaper_InsertsRecord() {
        HibernateNewspaperRepository repository = new HibernateNewspaperRepository(sessionFactory);
        Newspaper newsPaper = new Newspaper(new PeriodicalId(NEW_NEWSPAPER_ID));
        newsPaper.setName(NEW_NEWSPAPER_NAME);

        repository.save(newsPaper);

        NewspaperData newsPaperData = (NewspaperData) session.get(NewspaperData.class, NEW_NEWSPAPER_ID);
        assertThat(newsPaperData.getId(), is(NEW_NEWSPAPER_ID));
        assertThat(newsPaperData.getName(), is(NEW_NEWSPAPER_NAME));
    }

    @Test
    public void delete_WithExistingNewspaper_DeletesRecord() {
        HibernateNewspaperRepository repository = new HibernateNewspaperRepository(sessionFactory);

        repository.delete(new PeriodicalId(EXISTING_NEWSPAPER_1_ID));

        NewspaperData newsPaperData = (NewspaperData) session.get(NewspaperData.class, EXISTING_NEWSPAPER_1_ID);
        assertThat(newsPaperData, is(nullValue()));
    }

    @After
    public void rollBackTransaction() {
        session.getTransaction().rollback();
    }

    private static void SeedDatabase(Session session) {
        Transaction transaction = session.beginTransaction();

        NewspaperData newspaper1 = new NewspaperData();
        newspaper1.setId(EXISTING_NEWSPAPER_1_ID);
        newspaper1.setName(EXISTING_NEWSPAPER_1_NAME);
        session.save(newspaper1);

        NewspaperData newspaper2 = new NewspaperData();
        newspaper2.setId(EXISTING_NEWSPAPER_2_ID);
        newspaper2.setName(EXISTING_NEWSPAPER_2_NAME);
        session.save(newspaper2);

        transaction.commit();
    }
}