package net.gartee.openperiodical.core.commandhandlers;

import net.gartee.openperiodical.core.commands.RenameNewspaper;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RenameNewspaperHandler extends CommandHandler<RenameNewspaper>{
    private final NewspaperRepository newspaperRepository;

    @Autowired
    public RenameNewspaperHandler(NewspaperRepository newspaperRepository) {
        this.newspaperRepository = newspaperRepository;
    }

    @Override
    public void handle(RenameNewspaper command) {
        Newspaper newspaper = newspaperRepository.get(command.getNewspaperId());
        newspaper.setName(command.getNewName());

        newspaperRepository.save(newspaper);
    }
}