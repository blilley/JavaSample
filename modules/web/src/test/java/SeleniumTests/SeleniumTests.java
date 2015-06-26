package SeleniumTests;

import Selenium.Pages.CreatePage;
import Selenium.Pages.ListPage;
import net.gartee.openperiodical.core.persistence.entities.NewspaperData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SeleniumTests
{
    private static final UUID EXISTING_NEWSPAPER_1_ID = UUID.fromString("140060a3-c437-4be5-a533-001dfb1a9168");
    private static final String EXISTING_NEWSPAPER_1_NAME = "Selenium Newspaper 1";

    private static final UUID EXISTING_NEWSPAPER_2_ID = UUID.fromString("4544bb77-b977-491f-8cbd-49ea85cc1731");
    private static final String EXISTING_NEWSPAPER_2_NAME = "Selenium Newspaper 2";
    private final String baseUrl = "localhost:8080";

    private WebDriver driver;

    @Before
    public void setUp() throws IOException
    {
        seedDatabase(getSessionFactory().openSession());
        driver = new FirefoxDriver();
        driver.get(baseUrl);
    }

    @Test
    public void GivenExistingNewspapers_WhenIAmOnListPage_ThenIShouldSeeExistingNewspapers()
    {
        ListPage listPage = new ListPage(driver);
        Map<String, String> tableRows = listPage.getTableRows();

        assertThat(tableRows.keySet(), hasItem(containsString(EXISTING_NEWSPAPER_1_ID.toString())));
        assertThat(tableRows.get(EXISTING_NEWSPAPER_1_ID.toString()), is(EXISTING_NEWSPAPER_1_NAME));
        assertThat(tableRows.keySet(), hasItem(containsString(EXISTING_NEWSPAPER_1_ID.toString())));
        assertThat(tableRows.get(EXISTING_NEWSPAPER_2_ID.toString()), is(EXISTING_NEWSPAPER_2_NAME));
    }

    @Test
    public void GivenExistingNewspapers_WhenIClickDelete_ThenTheNewspaperShouldNotExist()
    {
        ListPage listPage = new ListPage(driver);
        listPage = listPage.deleteNewspaper(EXISTING_NEWSPAPER_1_NAME);

        Map<String, String> tableRows = listPage.getTableRows();
        assertThat(tableRows.keySet(), not(hasItem(containsString(EXISTING_NEWSPAPER_1_ID.toString()))));
        assertThat(tableRows.keySet(), hasItem(containsString(EXISTING_NEWSPAPER_2_ID.toString())));
        assertThat(tableRows.get(EXISTING_NEWSPAPER_2_ID.toString()), is(EXISTING_NEWSPAPER_2_NAME));
    }

    @Test
    public void GivenIAmOnListPage_WhenIClickCreate_ThenINavigateToCreatePage()
    {
        ListPage listPage = new ListPage(driver);

        CreatePage createPage = listPage.createNewsPaper();

        assertTrue(createPage.isVisible());
    }

    @Test
    public void GivenIAmOnCreatePage_WhenICreateANewspaper_ThenItShouldShowInListPage()
    {
        String newsPaperName = "Selenium Created Newspaper";
        driver.get(baseUrl + "/Create");
        CreatePage createPage = new CreatePage(driver);

        ListPage listPage = createPage.createNewspaper(newsPaperName);

        Map<String, String> tableRows = listPage.getTableRows();
        assertThat(tableRows.values(), hasItem(containsString(newsPaperName)));
    }

    @After
    public void cleanUp()
    {
        driver.quit();
    }

    private static void seedDatabase(Session session)
    {
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

    private static SessionFactory getSessionFactory() throws IOException
    {
        Properties applicationProperties = getApplicationProperties();

        Configuration configuration = getHibernateConfiguration(applicationProperties);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static Configuration getHibernateConfiguration(Properties applicationProperties)
    {
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.dialect", applicationProperties.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", applicationProperties.getProperty("jdbc.driverClassName"));
        configuration.setProperty("hibernate.connection.url", applicationProperties.getProperty("jdbc.url"));
        configuration.setProperty("hibernate.connection.username", applicationProperties.getProperty("jdbc.username"));
        configuration.setProperty("hibernate.connection.password", applicationProperties.getProperty("jdbc.password"));

        registerAnnotatedClasses(configuration);
        return configuration;
    }

    private static void registerAnnotatedClasses(Configuration configuration)
    {
        configuration.addAnnotatedClass(NewspaperData.class);
    }

    private static Properties getApplicationProperties() throws IOException
    {
        Properties applicationProperties = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
        applicationProperties.load(inputStream);
        return applicationProperties;
    }
}
