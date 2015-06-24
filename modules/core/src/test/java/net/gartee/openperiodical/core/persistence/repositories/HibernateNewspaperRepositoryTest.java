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

import java.util.UUID;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class HibernateNewspaperRepositoryTest {

    private static final UUID EXISTING_NEWSPAPER_ID = UUID.fromString("140060a3-c437-4be5-a533-001dfb1a9168");
    private static final String EXISTING_NEWSPAPER_NAME = "Existing Newspaper";
    private static final String UPDATED_NEWSPAPER_NAME = "Updated Newspaper";
    private static final UUID NEW_NEWSPAPER_ID = UUID.fromString("4544bb77-b977-491f-8cbd-49ea85cc1731");
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
        PeriodicalId newspaperId = new PeriodicalId(EXISTING_NEWSPAPER_ID);

        Newspaper newspaper = repository.get(newspaperId);

        assertThat(newspaper.getId(), is(newspaperId));
        assertThat(newspaper.getName(), is(EXISTING_NEWSPAPER_NAME));
    }

    @Test(expected = EntityDoesNotExistException.class)
    public void get_WithNonExistentId_ThrowsException() {
        HibernateNewspaperRepository repository = new HibernateNewspaperRepository(sessionFactory);
        PeriodicalId newspaperId = new PeriodicalId(NEW_NEWSPAPER_ID);

        repository.get(newspaperId);
    }

    @Test
    public void exists_WhenIdExists_ReturnsTrue() {
        HibernateNewspaperRepository repository = new HibernateNewspaperRepository(sessionFactory);
        PeriodicalId newspaperId = new PeriodicalId(EXISTING_NEWSPAPER_ID);

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
        Newspaper newsPaper = new Newspaper(new PeriodicalId(EXISTING_NEWSPAPER_ID));
        newsPaper.setName(UPDATED_NEWSPAPER_NAME);

        repository.save(newsPaper);

        NewspaperData newsPaperData = (NewspaperData) session.get(NewspaperData.class, EXISTING_NEWSPAPER_ID);
        assertThat(newsPaperData.getId(), is(EXISTING_NEWSPAPER_ID));
        assertThat(newsPaperData.getName(), is(UPDATED_NEWSPAPER_NAME));
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

        repository.delete(new PeriodicalId(EXISTING_NEWSPAPER_ID));

        NewspaperData newsPaperData = (NewspaperData) session.get(NewspaperData.class, EXISTING_NEWSPAPER_ID);
        assertThat(newsPaperData, is(nullValue()));
    }

    @After
    public void rollBackTransaction() {
        session.getTransaction().rollback();
    }

    private static void SeedDatabase(Session session) {
        Transaction transaction = session.beginTransaction();
        NewspaperData newspaper = new NewspaperData();
        newspaper.setId(EXISTING_NEWSPAPER_ID);
        newspaper.setName(EXISTING_NEWSPAPER_NAME);
        session.save(newspaper);
        transaction.commit();
    }
}