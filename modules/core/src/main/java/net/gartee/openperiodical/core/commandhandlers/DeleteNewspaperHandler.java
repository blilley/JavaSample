package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.openperiodical.core.commands.DeleteNewspaperCommand;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeleteNewspaperHandler extends CommandHandler<DeleteNewspaperCommand>{
    private final NewspaperRepository newspaperRepository;

    @Autowired
    public DeleteNewspaperHandler(NewspaperRepository newspaperRepository) {
        this.newspaperRepository = newspaperRepository;
    }

    @Override
    public void handle(DeleteNewspaperCommand command) {
        newspaperRepository.delete(command.getNewspaperId());
    }
}