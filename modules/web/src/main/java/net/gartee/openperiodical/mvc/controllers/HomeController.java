package net.gartee.openperiodical.mvc.controllers;

import net.gartee.common.CommandHandler;
import net.gartee.openperiodical.core.commands.RenameNewspaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
    //private final CommandHandler<CreateNewspaper> createNewspaperCommandHandler;
    private final CommandHandler<RenameNewspaper> renameNewspaperCommandHandler;

    @Autowired
    public HomeController(CommandHandler<RenameNewspaper> renameNewspaperCommandHandler) {
        this.renameNewspaperCommandHandler = renameNewspaperCommandHandler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {

        model.addAttribute("name", "you");

//        RenameNewspaper command = new RenameNewspaper(
//                new PeriodicalId(1),
//                "Renamed Newspaper"
//        );
//
//        renameNewspaperCommandHandler.handle(command);

        return "index"; // view name (jsp)
    }
}
