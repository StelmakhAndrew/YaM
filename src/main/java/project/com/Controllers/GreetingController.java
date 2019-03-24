package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.com.Entity.Book;
import project.com.Entity.User;
import project.com.Repository.UserRepository;
import project.com.Service.BookService;
import project.com.Service.UserService;

@Controller
public class GreetingController {

    @Autowired
    private UserService userService;
@Autowired
    private BookService bookService;




    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        User user = userService.findById(14L).orElse(new User());

//        Book book = new Book("dfs","sdf");
//        Book book1 = new Book("dfs2","sdf");
//        Book book2 = new Book("dfs3","sdf");
//        bookService.createBook(book);
//        bookService.createBook(book1);
//        bookService.createBook(book2);

//        user.setГName("SET DONE");
        userService.updateUser(user);

        //
//        User customer = new User("TestBOkkFinal", "Repository","sdf", new Book[]{book, book1, book2});

//        userService.createUser(customer);
//        System.out.println(user.getName());
//        System.out.println(user.getBooks().size());
//        System.out.println(user.getBooks().size());
//
        return "greeting";
    }

}
