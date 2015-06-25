package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.openperiodical.core.commands.CreateNewspaperCommand;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.exceptions.EntityAlreadyExistsException;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateNewspaperHandler extends CommandHandler<CreateNewspaperCommand> {
    private final NewspaperRepository newspaperRepository;

    @Autowired
    public CreateNewspaperHandler(NewspaperRepository repository) {
        this.newspaperRepository = repository;
    }

    @Override
    public void handle(CreateNewspaperCommand command) {
        Newspaper newspaper = new Newspaper(command.getNewspaperId());
        newspaper.setName(command.getNewspaperName());

        Guard.thatNameIsNotEmpty(newspaper);
        Guard.thatNewspaperDoesNotExist(newspaper, newspaperRepository);

        newspaperRepository.save(newspaper);
    }

    private static class Guard {
        private static final String EMPTY_NAME_MESSAGE_TEMPLATE = "Newspaper name cannot be empty (%1)";

        private static void thatNameIsNotEmpty(Newspaper newspaper) {
            if (newspaper.getName() == null || newspaper.getName().isEmpty()) {
                throw new IllegalArgumentException(buildMessage(newspaper.getId()));
            }
        }

        private static String buildMessage(PeriodicalId periodicalId) {
            return String.format(EMPTY_NAME_MESSAGE_TEMPLATE, periodicalId);
        }

        private static void thatNewspaperDoesNotExist(Newspaper newspaper, NewspaperRepository newspaperRepository) {
            if(newspaperRepository.exists(newspaper.getId())) {
                throw new EntityAlreadyExistsException(newspaper.getId());
            }
        }
    }
}
