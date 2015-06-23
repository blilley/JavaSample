package net.gartee.openperiodical.mvc.controllers;

import net.gartee.openperiodical.core.commandhandlers.CommandHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaper;
import net.gartee.openperiodical.core.commands.RenameNewspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.persistence.repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class HomeController {

    private CommandHandler<CreateNewspaper> createNewspaperCommandHandler;
    private CommandHandler<RenameNewspaper> renameNewspaperCommandHandler;
    private NewspaperRepository newspaperRepository;

    @Autowired
    public HomeController(CommandHandler<CreateNewspaper> createNewspaperCommandHandler,
                          CommandHandler<RenameNewspaper> renameNewspaperCommandHandler,
                          NewspaperRepository newspaperRepository)
    {
        this.createNewspaperCommandHandler = createNewspaperCommandHandler;
        this.renameNewspaperCommandHandler = renameNewspaperCommandHandler;
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
        //TODO: Change ID to be a GUID so we can generate it instead?
        CreateNewspaper command = new CreateNewspaper(new PeriodicalId(UUID.randomUUID()), title);

        createNewspaperCommandHandler.handle(command);

        return "redirect:/";
    }
    
    @RequestMapping(value = "Delete/{id}", method = RequestMethod.GET)
    public String deleteNewspaper(@PathVariable("id") UUID id)
    {
        //TODO: Call Delete handler?

        return "redirect:/";
    }
}
