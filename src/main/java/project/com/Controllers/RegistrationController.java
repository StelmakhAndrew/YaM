package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Role;
import project.com.Entity.User;
import project.com.Entity.UserDto;
import project.com.Service.UserService;

import javax.validation.Valid;
import java.util.Collections;


@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String confirm( @ModelAttribute("user") @Valid UserDto userDto, ModelMap model,
                           Errors errors) {
        System.out.println(userDto.getEmail());
        System.out.println(userService.findByEmail(userDto.getEmail())==null);
        System.out.println(userService.emailExist(userDto.getEmail()));
        if(!errors.hasErrors() && userService.emailExist(userDto.getEmail())) {

            User user = new User(userDto);

            user.setActive(true);
            user.setRoles(Collections.singleton(Role.ADMIN));
            userService.createUser(user);

            model.addAttribute("user", user);

            return "redirect:/user?id="+user.getId();
        }
        else {
            System.out.println(errors.getAllErrors().size());
            return "/registration";
        }
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(@ModelAttribute("user") User user, ModelMap model) {
        System.out.println("SUBMIT POST");
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getUsername());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userService.createUser(user);
        return "submit";
    }

    public String confirm(@ModelAttribute("user") User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        User users = userService.findByEmail(email);
        System.out.println(users);
        if (users.getPassword().equals(password)){
            System.out.println("done");
            return "redirect:/user?id="+users.getId();

        }
        System.out.println("Error");
        return "redirect:/greeting";
    }
}
