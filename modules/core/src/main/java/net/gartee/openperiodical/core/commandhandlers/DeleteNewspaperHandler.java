package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.openperiodical.core.commands.DeleteNewspaper;
import net.gartee.openperiodical.core.exceptions.EntityDoesNotExistException;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeleteNewspaperHandler extends CommandHandler<DeleteNewspaper>{
    private final NewspaperRepository newspaperRepository;

    @Autowired
    public DeleteNewspaperHandler(NewspaperRepository newspaperRepository) {
        this.newspaperRepository = newspaperRepository;
    }

    @Override
    public void handle(DeleteNewspaper command) {
        Guard.thatNewspaperExists(command.getNewspaperId(), newspaperRepository);

        newspaperRepository.delete(command.getNewspaperId());
    }

    private static class Guard {
        private static void thatNewspaperExists(PeriodicalId newspaperId, NewspaperRepository newspaperRepository) {
            if(!newspaperRepository.exists(newspaperId)) {
                throw new EntityDoesNotExistException(newspaperId);
            }
        }
    }
}