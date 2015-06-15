package net.gartee.openperiodical.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {

        model.addAttribute("name", "you");
        return "index"; // view name (jsp)
    }
}
