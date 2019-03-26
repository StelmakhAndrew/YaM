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
import project.com.Entity.Genre;
import project.com.Entity.User;
import project.com.Service.BookService;
import project.com.Service.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/bookAdd", method = RequestMethod.GET)
    public String bookAddForm(Model model) {
        List <Genre> genre = Arrays.asList(Genre.values());

        model.addAttribute("genres", genre);
        model.addAttribute("book", new Book());
        return "bookAdd";
    }

    @RequestMapping(value = "/bookAdd", method = RequestMethod.POST)
    public String submit(@ModelAttribute("book") Book book) {
        User user = userService.getCurrentUser();
        book.setDownloader(user);
        bookService.createBook(book);
        user.addBook(book);
        userService.updateUser(user);

        return "redirect:/bookById?id=" + book.getId();
    }


    @RequestMapping(value = "/bookById", method = RequestMethod.GET)
    public String submit(@RequestParam("id") Long id, Model model) {
        Book book = bookService.findById(id).orElse(new Book());
        model.addAttribute("name", book.getName());
        model.addAttribute("author", book.getAuthor());
        return "bookById";
    }

    @RequestMapping(value = "/allbook/search", method = RequestMethod.GET)
    public String search(ModelMap model, String search) {
        List<Book> allBooks = bookService.findBySearch(search);
        List<Genre> genre = Arrays.asList(Genre.values());
        model.addAttribute("genres", genre);
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("search", search);
        return "allBooks";
    }

    @RequestMapping(value = "/allbook/search/genre", method = RequestMethod.GET)
    public String searchGenre(ModelMap model, Integer id) {
        List<Genre> genre = Arrays.asList(Genre.values());
        List<Book> allBooks = bookService.findAllByGenre(Genre.values()[id]);
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("genres", genre);
        return "allBooks";
    }

    @RequestMapping(value = "/allbook", method = RequestMethod.GET)
    public String allBook(ModelMap model) {
        List<Book> allBooks = bookService.findAllOrderByRating();
        List<Genre> genre = Arrays.asList(Genre.values());
        model.addAttribute("genres", genre);
        model.addAttribute("allBooks", allBooks);
        return "allBooks";
    }


}
