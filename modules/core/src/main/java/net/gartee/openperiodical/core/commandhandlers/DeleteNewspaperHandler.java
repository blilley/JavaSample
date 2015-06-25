package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.openperiodical.core.commands.DeleteNewspaper;
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
        newspaperRepository.delete(command.getNewspaperId());
    }
}