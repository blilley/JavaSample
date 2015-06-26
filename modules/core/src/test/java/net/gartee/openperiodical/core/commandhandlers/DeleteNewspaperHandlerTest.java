package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.openperiodical.core.commands.DeleteNewspaper;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

public class DeleteNewspaperHandlerTest
{
    private static final UUID NEWSPAPER_ID = UUID.fromString("4544bb77-b977-491f-8cbd-49ea85cc1731");
    private static final String NEWSPAPER_NAME = "Newspaper";

    @Test
    public void handle_WithExistingNewspaper_DeleteNewspaper() {
        Newspaper newspaper = new Newspaper(new PeriodicalId(NEWSPAPER_ID));
        newspaper.setName(NEWSPAPER_NAME);

        NewspaperRepository repository = mock(NewspaperRepository.class);
        when(repository.exists(isA(PeriodicalId.class))).thenReturn(true);
        when(repository.get(isA(PeriodicalId.class))).thenReturn(newspaper);
        ArgumentCaptor<PeriodicalId> periodicalId = ArgumentCaptor.forClass(PeriodicalId.class);

        DeleteNewspaper command = new DeleteNewspaper(new PeriodicalId(NEWSPAPER_ID));
        DeleteNewspaperHandler handler = new DeleteNewspaperHandler(repository);
        handler.handle(command);

        verify(repository).delete(periodicalId.capture());
    }
}