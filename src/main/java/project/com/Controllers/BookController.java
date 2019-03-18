package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.com.Entity.Book;
import project.com.Entity.User;
import project.com.Service.BookService;
import project.com.Service.UserService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/bookAdd", method = RequestMethod.GET)
    public String bookAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookAdd";
    }

    @RequestMapping(value = "/bookById", method = RequestMethod.POST)
    public String submit(@ModelAttribute("book")Book book, ModelMap model) {
        model.addAttribute("name",book.getName());
        model.addAttribute("author",book.getAuthor());
        bookService.createBook(book);
        return "bookById";
    }



}
