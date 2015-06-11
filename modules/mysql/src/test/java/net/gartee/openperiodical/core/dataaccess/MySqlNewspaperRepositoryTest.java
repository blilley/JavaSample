package net.gartee.openperiodical.core.dataaccess;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    @Test
    public void exists_WhenIdExists_ReturnsTrue() {
        MySqlNewspaperRepository repository = new MySqlNewspaperRepository();
        PeriodicalId newspaperId = new PeriodicalId(EXISTING_NEWSPAPER_ID);

        assertTrue(repository.exists(newspaperId));
    }

    @Test
    public void exists_WhenIdDoesNotExist_ReturnsFalse() {
        MySqlNewspaperRepository repository = new MySqlNewspaperRepository();
        PeriodicalId newspaperId = new PeriodicalId(NEW_NEWSPAPER_ID);

        assertFalse(repository.exists(newspaperId));
    }

    @Test @Ignore
    public void save_WithExistingNewspaper_UpdatesRecord() {
        MySqlNewspaperRepository repository = new MySqlNewspaperRepository();
        Newspaper newsPaper = new Newspaper(new PeriodicalId(EXISTING_NEWSPAPER_ID));
        newsPaper.setName(UPDATED_NEWSPAPER_NAME);

        repository.save(newsPaper);

        // todo: assert
    }

    @Test @Ignore
    public void save_WithNewNewspaper_InsertsRecord() {
        MySqlNewspaperRepository repository = new MySqlNewspaperRepository();
        Newspaper newsPaper = new Newspaper(new PeriodicalId(NEW_NEWSPAPER_ID));
        newsPaper.setName(NEW_NEWSPAPER_NAME);

        repository.save(newsPaper);

        // todo: assert
    }

    @Test
    public void sandbox() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("test");
        dataSource.setPassword("mysql");
        dataSource.setServerName("192.168.56.102");
        dataSource.setDatabaseName("periodicalmanager");

        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM newspaper");

        int id = 0;
        String name = "";
        while(rs.next()) {
            id = rs.getInt("id");
            name = rs.getString("name");
        }

        rs.close();
        stmt.close();
        conn.close();

        assertThat(id, is(1));
        assertThat(name, is("Newspaper"));
    }
}