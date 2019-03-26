package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.com.Entity.User;
import project.com.Service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = GET)
    public String userProfile(@RequestParam("id") Long id, Model model){
        User user = userService.findById(id).orElse(null);
        model.addAttribute("user",user);
        return "user";
    }
    @RequestMapping(value = "/profile", method = GET)
    public String profile(Model model){
        User user = userService.getCurrentUser();
        if (user == null) return "redirect:/login";
        model.addAttribute("user",user);
        return "profile";
    }
}
