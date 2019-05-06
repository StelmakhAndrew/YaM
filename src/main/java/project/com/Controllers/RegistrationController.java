package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
        model.addAttribute("user", new UserDto());
        return "registration";
    }


    /**
     * The confirm() method checks if registration form sets up true and create new user.
     * @param userDto;
     * @param model;
     * @param errors;
     * @param result;
     * @return registration.html
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView confirm( @ModelAttribute("user") @Valid UserDto userDto, ModelMap model,
                           Errors errors, BindingResult result) {
        if (errors.hasErrors()) {
            result.rejectValue("email", "message.regError");
            result.rejectValue("password", "message.regError");
            result.rejectValue("matchingPassword", "message.regError");
            return new ModelAndView("/registration", "user", userDto);
        }
        if(!errors.hasErrors() &&
                userService.emailExist(userDto.getEmail()) &&
                userService.usernameExist(userDto.getUsername())) {

            User user = new User(userDto);
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userService.createUser(user);
            model.addAttribute("user", user);

            return new ModelAndView("redirect:/profile?id="+user.getId());
        }
        else {
            return new ModelAndView("/registration", "user", userDto);
        }
    }
}
