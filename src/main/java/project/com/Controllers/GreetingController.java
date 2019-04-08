package project.com.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("rating",2.5);
        return "greeting";
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String greetings(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model,
                            @RequestParam(name="rating", required=false, defaultValue="3") Integer rating) {
        model.addAttribute("rating",2.5);
        return "greeting";
    }
}
