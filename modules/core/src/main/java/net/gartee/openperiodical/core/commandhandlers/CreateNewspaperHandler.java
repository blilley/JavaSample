package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.cqrs.CommandHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaper;
import net.gartee.openperiodical.core.dataaccess.NewspaperRepository;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.exceptions.PeriodicalAlreadyExistsException;
import net.gartee.openperiodical.core.identities.PeriodicalId;

public class CreateNewspaperHandler implements CommandHandler<CreateNewspaper> {

    private final NewspaperRepository newspaperRepository;

    public CreateNewspaperHandler(NewspaperRepository repository) {
        this.newspaperRepository = repository;
    }

    public void handle(CreateNewspaper command) {
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
                throw new PeriodicalAlreadyExistsException(newspaper.getId());
            }
        }
    }
}