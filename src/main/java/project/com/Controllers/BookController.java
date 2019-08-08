package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.com.Entity.*;
import project.com.Service.BookService;
import project.com.Service.CommentService;
import project.com.Service.UserService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * The BookController class for control books.
 * @autor STS
 * @version 1.1
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    /**
     * The booAddForm() method returns bookform-page.
     *
     * @param model;
     * @return bookAdd.html
     */
    @RequestMapping(value = "/newbook", method = RequestMethod.GET)
    public String bookAddForm(Model model) {
        List<Genre> genre = Arrays.asList(Genre.values());
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) return "redirect:/login";

        model.addAttribute("genres", genre);
        model.addAttribute("book", new BookDto());
        return "bookAdd";
    }

    /**
     * The submit() method returns book-page after bookform-page and create book that you write in book-form.
     *
     * @param bookDto;
     * @return bookById.html
     * @throws IOException
     */
    @RequestMapping(value = "/newbook", method = RequestMethod.POST)
    public String submit(@ModelAttribute("book") BookDto bookDto) throws IOException {
        String uuidFileName = UUID.randomUUID().toString();
        MultipartFile file = bookDto.getImage();
        String resultFileName = uuidFileName + "." + file.getOriginalFilename();
        file.transferTo(new File("E:\\project]\\YaM\\src\\main\\resources\\static\\images\\books\\" + resultFileName));

        String uuidFileNameBook = UUID.randomUUID().toString();
        MultipartFile fileBook = bookDto.getBook();
        String resultFileNameBook = uuidFileNameBook + "." + fileBook.getOriginalFilename();
        fileBook.transferTo(new File("E:\\project]\\YaM\\src\\main\\resources\\static\\images\\books\\" + resultFileNameBook));

        User user = userService.getCurrentUser();
        Book book = new Book(bookDto);
        book.setImage("../images/books/" + resultFileName);
        book.setBook("E:\\project]\\YaM\\src\\main\\resources\\static\\books\\" + resultFileNameBook);
        book.setDownloader(user);
        bookService.createBook(book);
        user.addBook(book);
        userService.updateUser(user);
        return "redirect:/bookById?id=" + book.getId();
    }


    /**
     * The submit() method returns book-page .
     *
     * @param id;
     * @param model;
     * @return bookById.html
     * @throws IOException;
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public String submit(@PathVariable("id") Long id, Model model) throws IOException {
        Book book = bookService.findById(id).orElse(new Book());

        List<Comment> comments = commentService.findComentsForThisBookSortByDate(book.getId());

        User currentUser = userService.getCurrentUser();

        model.addAttribute("comments", comments);
        model.addAttribute("book", book);
        model.addAttribute("comment", new Comment());
        model.addAttribute("user", false);
        return "bookById";
    }


    /**
     * The submit() method returns book-page , sets up rating and comments.
     *
     * @param id;
     * @param comment;
     * @return bookById.html
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.POST)
    public String submitComment(@PathVariable("id") Long id, @RequestParam(name = "comment") String comment, Model model) {

        Book book = bookService.findById(id).orElse(new Book());
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) return "redirect:/login";
        System.out.println("comment" + comment);

        setComment(book, comment, currentUser);

        List<Comment> comments = commentService.findComentsForThisBookSortByDate(book.getId());

        model.addAttribute("comments", comments);
        model.addAttribute("user", currentUser);

        model.addAttribute("book", book);
        return "redirect:/books/" + book.getId();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PATCH)
    public String submitRating(@PathVariable("id") Long id,
                               @RequestParam(name = "rating")
                                       Integer rating, Model model) {

        System.out.println("1");
        Book book = bookService.findById(id).orElse(new Book());
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) return "redirect:/login";
        System.out.println("rating" + rating);

        setRating(book, rating);

        List<Comment> comments = commentService.findComentsForThisBookSortByDate(book.getId());

        model.addAttribute("comments", comments);
        model.addAttribute("book", book);
        model.addAttribute("user", currentUser);

        return "redirect:/books/" + book.getId();
    }

    /**
     * The search() method find book.
     *
     * @param model;
     * @param search;
     * @return allBooks.html
     */
    @RequestMapping(value = "/books/search", method = RequestMethod.GET)
    public String search(ModelMap model, String search) {
        List<Book> allBooks = bookService.findBySearch(search);
        List<Genre> genre = Arrays.asList(Genre.values());
        model.addAttribute("genres", genre);
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("search", search);
        return "allBooks";
    }

    /**
     * The searchGenre() method find all book by genre.
     *
     * @param model;
     * @param id;
     * @return allBooks.html
     */
    @RequestMapping(value = "/books/search/genre", method = RequestMethod.GET)
    public String searchGenre(ModelMap model, Integer id) {
        List<Genre> genre = Arrays.asList(Genre.values());
        List<Book> allBooks = bookService.findAllByGenre(Genre.values()[id - 1]);
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("genres", genre);
        return "allBooks";
    }

    /**
     * The searchAuthor() method find all book by author.
     *
     * @param model;
     * @param author;
     * @return allBooks.html
     */
    @RequestMapping(value = "/books/search/author", method = RequestMethod.GET)
    public String searchAuthor(ModelMap model, String author) {
        List<Genre> genre = Arrays.asList(Genre.values());
        List<Book> allBooks = bookService.findAllByAuthor(author);
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("genres", genre);
        return "allBooks";
    }


    /**
     * The allBook() method find all book.
     *
     * @param model;
     * @return allBooks.html
     */
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String allBook(ModelMap model) {
        List<Book> allBooks = bookService.findAllOrderByRating();
        List<Genre> genre = Arrays.asList(Genre.values());
        model.addAttribute("genres", genre);
        model.addAttribute("allBooks", allBooks);
        return "allBooks";
    }

    /**
     * The setRating() method calculates and sets up rating.
     *
     * @param book;
     * @param rating;
     */
    private void setRating(Book book, int rating) {
        System.out.println("setRating");
        float ratingOld = book.getRating();
        int count = book.getCountRating();
        float ratingNew = (ratingOld * count + rating) / (count + 1);
        book.setRating(ratingNew);
        book.setCountRating(count + 1);
        bookService.updateBook(book);
        System.out.println("setRating END");

    }

    /**
     * The setComment() method sets up comment.
     *
     * @param book;
     * @param comment;
     * @param currentUser;
     */
    private void setComment(Book book, String comment, User currentUser) {
        Comment newComment = new Comment(comment);
        Date date = Date.valueOf(LocalDate.now());
        newComment.setDate(date);
        newComment.setBook(book);
        newComment.setUser(currentUser);
        commentService.createComment(newComment);
        book.addComments(newComment);
        bookService.updateBook(book);
        currentUser.addComments(newComment);
        userService.updateUser(currentUser);
    }
}