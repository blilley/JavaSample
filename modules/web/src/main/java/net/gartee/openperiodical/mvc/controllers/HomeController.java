package net.gartee.openperiodical.mvc.controllers;

import net.gartee.openperiodical.core.commandhandlers.CommandHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaper;
import net.gartee.openperiodical.core.commands.DeleteNewspaper;
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
import java.util.UUID;

@Controller
@RequestMapping("/")
public class HomeController {
    private CommandHandler<CreateNewspaper> createNewspaperCommandHandler;
    private CommandHandler<DeleteNewspaper> deleteNewspaperCommandHandler;
    private Query<ListNewspapersCriteria, List<Newspaper>> listNewspapersQuery;

    @Autowired
    public HomeController(CommandHandler<CreateNewspaper> createNewspaperCommandHandler,
                          CommandHandler<DeleteNewspaper> deleteNewspaperCommandHandler,
                          Query<ListNewspapersCriteria, List<Newspaper>> listNewspapersQuery) {
        this.createNewspaperCommandHandler = createNewspaperCommandHandler;
        this.deleteNewspaperCommandHandler = deleteNewspaperCommandHandler;
        this.listNewspapersQuery = listNewspapersQuery;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getList(ModelMap model) {
        List<Newspaper> newspapers = listNewspapersQuery.execute(
            new ListNewspapersCriteria());

        // todo: convert domain models to view models

        model.addAttribute("newspapers", newspapers);
        return "index";
    }

    @RequestMapping(value = "Create", method = RequestMethod.GET)
    public String createNewsPaper()
    {
        return "createNewspaper";
    }

    @RequestMapping(value = "Create", method = RequestMethod.POST)
    public String createNewsPaper(@RequestParam String title)
    {
        CreateNewspaper command = new CreateNewspaper(
                PeriodicalId.newId(),
                title);

        createNewspaperCommandHandler.handle(command);
        // alt. send to domain command bus

        return "redirect:/";
    }

    @RequestMapping(value = "Delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteNewspaper(@PathVariable("id") UUID id)
    {
        DeleteNewspaper command = new DeleteNewspaper(
            new PeriodicalId(id));
        deleteNewspaperCommandHandler.handle(command);
    }
}
