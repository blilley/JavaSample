package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.openperiodical.core.commands.RenameNewspaperCommand;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.exceptions.EntityDoesNotExistException;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RenameNewspaperHandler extends CommandHandler<RenameNewspaperCommand>{
    private final NewspaperRepository newspaperRepository;

    @Autowired
    public RenameNewspaperHandler(NewspaperRepository newspaperRepository) {
        this.newspaperRepository = newspaperRepository;
    }

    @Override
    public void handle(RenameNewspaperCommand command) {
        Guard.thatNewspaperExists(command.getNewspaperId(), newspaperRepository);

        Newspaper newspaper = newspaperRepository.get(command.getNewspaperId());
        newspaper.setName(command.getNewName());

        newspaperRepository.save(newspaper);
    }

    private static class Guard {
        private static void thatNewspaperExists(PeriodicalId newspaperId, NewspaperRepository newspaperRepository) {
            if(!newspaperRepository.exists(newspaperId)) {
                throw new EntityDoesNotExistException(newspaperId);
            }
        }
    }
}