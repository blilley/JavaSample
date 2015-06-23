package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.openperiodical.core.commands.RenameNewspaper;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

public class RenameNewspaperHandlerTest {

    private static final int NEWSPAPER_ID = 1;
    private static final String NEWSPAPER_NAME = "Newspaper";
    private static final String NEWSPAPER_RENAME = "Newspaper Renamed";

    @Test
    public void handle_WithExistingNewspaper_RenamesNewspaper() {
        Newspaper newspaper = new Newspaper(new PeriodicalId(NEWSPAPER_ID));
        newspaper.setName(NEWSPAPER_NAME);

        NewspaperRepository repository = mock(NewspaperRepository.class);
        when(repository.get(isA(PeriodicalId.class))).thenReturn(newspaper);
        ArgumentCaptor<Newspaper> newspaperCaptor = ArgumentCaptor.forClass(Newspaper.class);

        RenameNewspaper command = new RenameNewspaper(new PeriodicalId(NEWSPAPER_ID), NEWSPAPER_RENAME);
        RenameNewspaperHandler handler = new RenameNewspaperHandler(repository);
        handler.handle(command);

        verify(repository).save(newspaperCaptor.capture());
        assertThat(newspaperCaptor.getValue().getId(), is(new PeriodicalId(NEWSPAPER_ID) ));
        assertThat(newspaperCaptor.getValue().getName(), is(NEWSPAPER_RENAME ));
    }
}