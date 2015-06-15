package net.gartee.openperiodical.data.mysql;

import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.exceptions.EntityDoesNotExistException;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.data.entities.NewspaperData;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MySqlNewspaperRepositoryTest {

    private static final int EXISTING_NEWSPAPER_ID = 1;
    private static final String EXISTING_NEWSPAPER_NAME = "Existing Newspaper";
    private static final String UPDATED_NEWSPAPER_NAME = "Updated Newspaper";
    private static final int NEW_NEWSPAPER_ID = 2;
    private static final String NEW_NEWSPAPER_NAME = "New Newspaper";

    private Session session;
    private Transaction transaction;

    @BeforeClass
    public static void setupDatabase() {
        SeedDatabase(HibernateUtil.getSessionFactory().getCurrentSession());
    }

    @Before
    public void setupSession() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
    }

    @Test
    public void get_WithExistingId_ReturnsNewspaper() {
        MySqlNewspaperRepository repository = new MySqlNewspaperRepository(session);
        PeriodicalId newspaperId = new PeriodicalId(EXISTING_NEWSPAPER_ID);

        Newspaper newspaper = repository.get(newspaperId);

        assertThat(newspaper.getId(), is(newspaperId));
        assertThat(newspaper.getName(), is(EXISTING_NEWSPAPER_NAME));
    }

    @Test(expected = EntityDoesNotExistException.class)
    public void get_WithNonExistentId_ThrowsException() {
        MySqlNewspaperRepository repository = new MySqlNewspaperRepository(session);
        PeriodicalId newspaperId = new PeriodicalId(NEW_NEWSPAPER_ID);

        repository.get(newspaperId);
    }

    @Test
    public void exists_WhenIdExists_ReturnsTrue() {
        MySqlNewspaperRepository repository = new MySqlNewspaperRepository(session);
        PeriodicalId newspaperId = new PeriodicalId(EXISTING_NEWSPAPER_ID);

        assertTrue(repository.exists(newspaperId));
    }

    @Test
    public void exists_WhenIdDoesNotExist_ReturnsFalse() {
        MySqlNewspaperRepository repository = new MySqlNewspaperRepository(session);
        PeriodicalId newspaperId = new PeriodicalId(NEW_NEWSPAPER_ID);

        assertFalse(repository.exists(newspaperId));
    }

    @Test
    public void save_WithExistingNewspaper_UpdatesRecord() {
        MySqlNewspaperRepository repository = new MySqlNewspaperRepository(session);
        Newspaper newsPaper = new Newspaper(new PeriodicalId(EXISTING_NEWSPAPER_ID));
        newsPaper.setName(UPDATED_NEWSPAPER_NAME);

        repository.save(newsPaper);

        NewspaperData newsPaperData = (NewspaperData) session.get(NewspaperData.class, EXISTING_NEWSPAPER_ID);
        assertThat(newsPaperData.getId(), is(EXISTING_NEWSPAPER_ID));
        assertThat(newsPaperData.getName(), is(UPDATED_NEWSPAPER_NAME));
    }

    @Test
    public void save_WithNewNewspaper_InsertsRecord() {
        MySqlNewspaperRepository repository = new MySqlNewspaperRepository(session);
        Newspaper newsPaper = new Newspaper(new PeriodicalId(NEW_NEWSPAPER_ID));
        newsPaper.setName(NEW_NEWSPAPER_NAME);

        repository.save(newsPaper);

        NewspaperData newsPaperData = (NewspaperData) session.get(NewspaperData.class, NEW_NEWSPAPER_ID);
        assertThat(newsPaperData.getId(), is(NEW_NEWSPAPER_ID));
        assertThat(newsPaperData.getName(), is(NEW_NEWSPAPER_NAME));
    }

    @After
    public void rollBackTransaction() {
        transaction.rollback();
    }

    @AfterClass
    public static void destroyDatabase() {

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