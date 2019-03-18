package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.com.Entity.User;
import project.com.Service.UserService;


@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm() {
        return "registration";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(@RequestParam(name="name") String name,
                         @RequestParam(name="email") String email,
                         @RequestParam(name="password") String password, ModelMap model) {
        model.addAttribute("email",email);
        User user = new User(name,email,password);

        userService.createUser(user);
        return "submit";
    }

}
