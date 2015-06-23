package net.gartee.openperiodical.mvc.controllers;

import net.gartee.cqrs.CommandHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaper;
import net.gartee.openperiodical.core.entities.Newspaper;
import net.gartee.openperiodical.core.identities.PeriodicalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
        List<Newspaper> newspapers = new ArrayList();
        model.addAttribute("newspapers", newspapers);
        return "index"; // view name (jsp)
    }

    @RequestMapping(value = "Create", method = RequestMethod.POST)
    public String createNewsPaper(@RequestParam String title)
    {
        CreateNewspaper command = new CreateNewspaper(new PeriodicalId(2), title);

        createNewspaperCommandHandler.handle(command);

        return "redirect:/";
    }

    @RequestMapping(value = "Delete/{id}", method = RequestMethod.DELETE)
    public String deleteNewspaper(@PathVariable("id") int id)
    {
        //TODO: Call Delete handler?

        return "redirect:/";
    }
}
