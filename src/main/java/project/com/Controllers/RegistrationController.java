package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Role;
import project.com.Entity.User;
import project.com.Entity.UserDto;
import project.com.Service.UserService;

import javax.validation.Valid;
import java.util.Collections;


/**
 *
 * The RegistrationController class for control registration.
 * @autor STS
 * @version 1.1
 */
@Controller
public class RegistrationController {

    /**
     *
     */
    @Autowired
    private UserService userService;

    /**
     * The registrationForm() method return registration form.
     * @param model;
     * @return registration.html
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registration";
    }


    /**
     * The confirm() method checks if registration form sets up true and create new user.
     * @param userDto;
     * @param model;
     * @param bindingResult;
     * @return registration.html
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String confirm(@Valid UserDto userDto,   BindingResult bindingResult, Model model
                          ) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        else if (!bindingResult.hasErrors() &&
                userService.emailExist(userDto.getEmail()) &&
                userService.usernameExist(userDto.getUsername())) {

            User newUser = new User(userDto);
            newUser.setActive(true);
            newUser.setRoles(Collections.singleton(Role.USER));
            userService.createUser(newUser);
            model.addAttribute("user", newUser);

            return "redirect:/login";
        }
        return "registration";

    }
}
