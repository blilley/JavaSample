package net.gartee.openperiodical.mvc.controllers;

import net.gartee.openperiodical.core.commandhandlers.CommandHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaper;
import net.gartee.openperiodical.core.commands.DeleteNewspaper;
import net.gartee.openperiodical.core.commands.RenameNewspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class HomeController {

    private CommandHandler<CreateNewspaper> createNewspaperCommandHandler;
    private CommandHandler<RenameNewspaper> renameNewspaperCommandHandler;
    private CommandHandler<DeleteNewspaper> deleteNewspaperCommandHandler;
    private NewspaperRepository newspaperRepository;

    @Autowired
    public HomeController(CommandHandler<CreateNewspaper> createNewspaperCommandHandler,
                          CommandHandler<RenameNewspaper> renameNewspaperCommandHandler,
                          CommandHandler<DeleteNewspaper> deleteNewspaperCommandHandler,
                          NewspaperRepository newspaperRepository)
    {
        this.createNewspaperCommandHandler = createNewspaperCommandHandler;
        this.renameNewspaperCommandHandler = renameNewspaperCommandHandler;
        this.deleteNewspaperCommandHandler = deleteNewspaperCommandHandler;
        this.newspaperRepository = newspaperRepository;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("newspapers", newspaperRepository.getAll());
        return "index";
    }

    @RequestMapping(value = "Create", method = RequestMethod.POST)
    public String createNewsPaper(@RequestParam String title)
    {
        CreateNewspaper command = new CreateNewspaper(new PeriodicalId(UUID.randomUUID()), title);

        createNewspaperCommandHandler.handle(command);

        return "redirect:/";
    }
    
    @RequestMapping(value = "Delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteNewspaper(@PathVariable("id") UUID id)
    {
        DeleteNewspaper command = new DeleteNewspaper(new PeriodicalId(id));

        deleteNewspaperCommandHandler.handle(command);
    }
}
