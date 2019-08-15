package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.com.Entity.Book;
import project.com.Entity.User;
import project.com.Service.UserService;
import project.com.Service.BookService;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * The UserController class for control user.
 * @autor STS
 * @version 1.1
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * The userProfile() method return user's page.
     * @param id;
     * @param model;
     * @return user.html
     */
    @RequestMapping(value = "/users/{id}", method = GET)
    public String userProfile(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id).orElse(null);
        model.addAttribute("user",user);
        return "user";
    }

    /**
     * The profile() method return user's page where you can see all books that this user download.
     * @param model;
     * @return profile.page
     */
    @RequestMapping(value = "/profiles", method = GET)
    public String profile(Model model){
        User user = userService.getCurrentUser();
        if (user == null) return "redirect:/login";

        List<Book> allBooksWhatPublickByUser = user.getBooks();

        List<Book> allFavouriteBooks = user.getFavouriteBooks();

        model.addAttribute("user",user);
        model.addAttribute("allBooks",allBooksWhatPublickByUser);
        model.addAttribute("allFavouriteBooks",allFavouriteBooks);
        return "profile";
    }
}
