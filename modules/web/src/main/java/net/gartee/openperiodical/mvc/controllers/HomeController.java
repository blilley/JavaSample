package net.gartee.openperiodical.mvc.controllers;

import net.gartee.openperiodical.core.commandhandlers.CommandHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaperCommand;
import net.gartee.openperiodical.core.commands.RenameNewspaperCommand;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import net.gartee.openperiodical.core.queries.ListNewspapersCriteria;
import net.gartee.openperiodical.core.queries.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private CommandHandler<CreateNewspaperCommand> createNewspaperCommandHandler;
    private CommandHandler<RenameNewspaperCommand> renameNewspaperCommandHandler;
    private Query<ListNewspapersCriteria, List<Newspaper>> listNewspapersQuery;

    @Autowired
    public HomeController(CommandHandler<CreateNewspaperCommand> createNewspaperCommandHandler,
                          CommandHandler<RenameNewspaperCommand> renameNewspaperCommandHandler,
                          Query<ListNewspapersCriteria, List<Newspaper>> listNewspapersQuery) {
        this.createNewspaperCommandHandler = createNewspaperCommandHandler;
        this.renameNewspaperCommandHandler = renameNewspaperCommandHandler;
        this.listNewspapersQuery = listNewspapersQuery;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("name", "you");

        List<Newspaper> newspapers = listNewspapersQuery.execute(
            new ListNewspapersCriteria());

        renameNewspaperCommandHandler.handle(
                new RenameNewspaperCommand(new PeriodicalId(1), "rawr!")
        );

        return "index"; // view name (jsp)
    }
}
