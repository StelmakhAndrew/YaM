package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.com.Entity.User;
import project.com.Repository.UserRepository;
import project.com.Service.UserService;

@Controller
public class GreetingController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        User customer = new User("dsf", "Repository","sdf");
        userService.createUser(customer);
        return "greeting";
    }

}
