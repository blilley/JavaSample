package net.gartee.openperiodical.mvc.controllers;

import net.gartee.openperiodical.core.commandhandlers.CommandHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaperCommand;
import net.gartee.openperiodical.core.commands.RenameNewspaperCommand;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CommandHandler<CreateNewspaperCommand> createNewspaperCommandHandler;

    @Autowired
    private CommandHandler<RenameNewspaperCommand> renameNewspaperCommandHandler;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("name", "you");

        renameNewspaperCommandHandler.handle(
                new RenameNewspaperCommand(new PeriodicalId(5), "rawr!")
        );

        return "index"; // view name (jsp)
    }
}
