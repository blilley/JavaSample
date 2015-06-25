package net.gartee.openperiodical.mvc.controllers;

import net.gartee.openperiodical.core.commandhandlers.CommandHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaperCommand;
import net.gartee.openperiodical.core.commands.DeleteNewspaperCommand;
import net.gartee.openperiodical.core.commands.RenameNewspaperCommand;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.queries.ListNewspapersCriteria;
import net.gartee.openperiodical.core.queries.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.transaction.Transactional;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class HomeController {
    private CommandHandler<CreateNewspaperCommand> createNewspaperCommandHandler;
    private CommandHandler<RenameNewspaperCommand> renameNewspaperCommandHandler;
    private CommandHandler<DeleteNewspaperCommand> deleteNewspaperCommandHandler;
    private Query<ListNewspapersCriteria, List<Newspaper>> listNewspapersQuery;

    @Autowired
    public HomeController(CommandHandler<CreateNewspaperCommand> createNewspaperCommandHandler,
                          CommandHandler<RenameNewspaperCommand> renameNewspaperCommandHandler,
                          CommandHandler<DeleteNewspaperCommand> deleteNewspaperCommandHandler,
                          Query<ListNewspapersCriteria, List<Newspaper>> listNewspapersQuery) {
        this.createNewspaperCommandHandler = createNewspaperCommandHandler;
        this.renameNewspaperCommandHandler = renameNewspaperCommandHandler;
        this.deleteNewspaperCommandHandler = deleteNewspaperCommandHandler;
        this.listNewspapersQuery = listNewspapersQuery;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        List<Newspaper> newspapers = listNewspapersQuery.execute(
                new ListNewspapersCriteria());
        model.addAttribute("newspapers", newspapers);
        return "index";
    }

    @RequestMapping(value = "Create", method = RequestMethod.POST)
    public String createNewsPaper(@RequestParam String title)
    {
        CreateNewspaperCommand command = new CreateNewspaperCommand(new PeriodicalId(UUID.randomUUID()), title);

        createNewspaperCommandHandler.handle(command);

        return "redirect:/";
    }

    @RequestMapping(value = "Delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteNewspaper(@PathVariable("id") UUID id)
    {
        DeleteNewspaperCommand command = new DeleteNewspaperCommand(new PeriodicalId(id));

        deleteNewspaperCommandHandler.handle(command);
    }
}
