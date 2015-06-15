package net.gartee.openperiodical.mvc;

import net.gartee.cqrs.CommandHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
    private final CommandHandler<CreateNewspaper> createNewspaperCommandHandler;

    @Autowired
    public HomeController(CommandHandler<CreateNewspaper> createNewspaperCommandHandler) {
        this.createNewspaperCommandHandler = createNewspaperCommandHandler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {

        model.addAttribute("name", "you");

        CreateNewspaper command = new CreateNewspaper(
                new PeriodicalId(7),
                "Web Newspaper"
        );

        createNewspaperCommandHandler.handle(command);

        return "index"; // view name (jsp)
    }
}
