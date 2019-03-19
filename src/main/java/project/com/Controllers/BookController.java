package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.com.Entity.Book;
import project.com.Entity.User;
import project.com.Service.BookService;
import project.com.Service.UserService;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/bookAdd", method = RequestMethod.GET)
    public String bookAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookAdd";
    }

    @RequestMapping(value = "/bookAdd", method = RequestMethod.POST)
    public String submit(@ModelAttribute("book")Book book) {
        bookService.createBook(book);
        return "redirect:/bookById?id="+book.getId();
    }



    @RequestMapping(value = "/bookById", method = RequestMethod.GET)
    public String submit(@RequestParam("id") Long id, Model model) {
        Book book = bookService.findById(id).orElse(new Book());
        model.addAttribute("name",book.getName());
        model.addAttribute("author",book.getAuthor());
        return "bookById";
    }

    @RequestMapping(value = "/allbook", method = RequestMethod.GET)
    public String allBook(ModelMap model) {
        List<Book> allBooks = bookService.findAllBook();
        model.addAttribute("allBooks",allBooks);
        return "allBooks";
    }



}
