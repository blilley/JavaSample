package net.gartee.openperiodical.mvc.controllers;

import net.gartee.openperiodical.core.commandhandlers.CommandHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaper;
import net.gartee.openperiodical.core.commands.RenameNewspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CommandHandler<CreateNewspaper> createNewspaperCommandHandler;

    @Autowired
    private CommandHandler<RenameNewspaper> renameNewspaperCommandHandler;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
//        model.addAttribute("name", "you");
//
//        renameNewspaperCommandHandler.handle(
//                new RenameNewspaper(new PeriodicalId(UUID.randomUUID()), "rawr!")
//        );

        return "index"; // view name (jsp)
    }
}
