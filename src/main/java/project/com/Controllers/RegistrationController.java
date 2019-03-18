package project.com.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm() {
        return "registration";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(@RequestParam(name="name", required=false, defaultValue="World") String name,@RequestParam(name="id", required=false, defaultValue="34") Long id, ModelMap model) {
        System.out.println("DONE");
        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return "submit";
    }

}
