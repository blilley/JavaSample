package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.openperiodical.core.commands.CreateNewspaper;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateNewspaperHandlerTest {

    private static final UUID NEWSPAPER_ID = UUID.fromString("4544bb77-b977-491f-8cbd-49ea85cc1731");
    private static final String NEWSPAPER_NAME = "Newspaper";

    @Test
    public void handle_WithValidValues_CreatesNewNewspaperAndPersists() {
        NewspaperRepository repository = mock(NewspaperRepository.class);
        ArgumentCaptor<Newspaper> newspaperCaptor = ArgumentCaptor.forClass(Newspaper.class);

        CreateNewspaper command = new CreateNewspaper(new PeriodicalId(NEWSPAPER_ID), NEWSPAPER_NAME);
        CreateNewspaperHandler handler = new CreateNewspaperHandler(repository);
        handler.handle(command);

        verify(repository).save(newspaperCaptor.capture());
        assertThat(newspaperCaptor.getValue().getId(), is(new PeriodicalId(NEWSPAPER_ID) ));
        assertThat(newspaperCaptor.getValue().getName(), is(NEWSPAPER_NAME ));
    }

    @Test(expected = IllegalArgumentException.class)
    public void handle_WithNullNewspaperName_ThrowsException() {
        NewspaperRepository repository = mock(NewspaperRepository.class);

        CreateNewspaper command = new CreateNewspaper(new PeriodicalId(NEWSPAPER_ID), null);
        CreateNewspaperHandler handler = new CreateNewspaperHandler(repository);
        handler.handle(command);
    }

    @Test(expected = IllegalArgumentException.class)
    public void handle_WithEmptyNewspaperName_ThrowsException() {
        NewspaperRepository repository = mock(NewspaperRepository.class);

        CreateNewspaper command = new CreateNewspaper(new PeriodicalId(NEWSPAPER_ID), "");
        CreateNewspaperHandler handler = new CreateNewspaperHandler(repository);
        handler.handle(command);
    }
}