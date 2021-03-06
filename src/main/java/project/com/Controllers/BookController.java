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
import project.com.Service.GenreService;
import project.com.Service.UserService;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static java.util.Arrays.asList;


/**
 * The BookController class for control books.
 *
 * @version 1.1
 * @autor STS
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private GenreService genreService;

    /**
     * The booAddForm() method returns bookform-page.
     *
     * @param model;
     * @return bookAdd.html
     */
    @RequestMapping(value = "/newbook", method = RequestMethod.GET)
    public String bookAddForm(Model model) {
        List<String> genre = new ArrayList<>();
        genre.add("asas");
        genre.add("asa");
        genre.add("qw");
//        List<Genre> genre = genreService.findAllGenre();
        Optional<User> currentUser = userService.getCurrentUser();
        if (!currentUser.isPresent()) return "redirect:/login";

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
    //ToDo: Max size photo 1mb
    @RequestMapping(value = "/newbook", method = RequestMethod.POST)
    public String submit(@ModelAttribute("book") BookDto bookDto) throws IOException {
        System.out.println("11111111111");
        System.out.println("11111111111");
        System.out.println("11111111111");
        System.out.println("11111111111");
        System.out.println("11111111111");
        System.out.println("11111111111");
        System.out.println("11111111111");
        System.out.println("11111111111");
        System.out.println("11111111111");
        String uuidFileName = UUID.randomUUID().toString();
        MultipartFile file = bookDto.getImage();
        String resultFileName = uuidFileName + "." + file.getOriginalFilename();
        file.transferTo(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\books\\" + resultFileName));

        String uuidFileNameBook = UUID.randomUUID().toString();
        MultipartFile fileBook = bookDto.getBook();
        String resultFileNameBook = uuidFileNameBook + "." + fileBook.getOriginalFilename();
        fileBook.transferTo(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\books\\" + resultFileNameBook));

        User user = userService.getCurrentUser().get();
        Book book = new Book(bookDto);
        book.setImage("../images/books/" + resultFileName);
        book.setBook(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\books\\" + resultFileNameBook);
        book.setDownloader(user);
        bookService.createBook(book);
        user.addBook(book);
        userService.updateUser(user);
        return "redirect:/books/" + book.getId();
    }


    /**
     * The submit() method returns book-page .
     *
     * @param id;
     * @param model;
     * @return bookById.html
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public String submit(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id).get();

        Boolean isFavourite = null;
        boolean canSetRating = false;

        List<Comment> comments = commentService.findComentsForThisBookSortByDate(book.getId());

        Optional<User> currentUser = userService.getCurrentUser();

        if (currentUser.isPresent()) {
            isFavourite = currentUser.get().getFavouriteBooks().contains(book);
            canSetRating = true;
        }
        model.addAttribute("comments", comments);
        model.addAttribute("book", book);
        model.addAttribute("comment", new Comment());
        model.addAttribute("user", canSetRating);
        model.addAttribute("isFavourite", isFavourite);
        return "bookById";
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.POST, params = "action=favourite")
    public String addToFavourite(@PathVariable("id") Long id) {
        Book book = bookService.findById(id).orElse(new Book());

        Optional<User> currentUser = userService.getCurrentUser();

        if (!currentUser.isPresent()) return "redirect:/login";

        bookService.addToFavourite(currentUser.get(), book);

        return "redirect:/books/" + book.getId();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.POST, params = "action=notFavourite")
    public String removeFavourite(@PathVariable("id") Long id) {
        Book book = bookService.findById(id).orElse(new Book());

        Optional<User> currentUser = userService.getCurrentUser();

        if (!currentUser.isPresent()) return "redirect:/login";

        bookService.removeFromFavourite(currentUser.get(), book);

        return "redirect:/books/" + book.getId();
    }


    /**
     * The submit() method returns book-page , sets up rating and comments.
     *
     * @param id;
     * @param comment;
     * @return bookById.html
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.POST, params = "action=comment")
    public String submitComment(@PathVariable("id") Long id, @RequestParam(name = "comment") String comment, Model model) {

        Book book = bookService.findById(id).orElse(new Book());
        Optional<User> currentUser = userService.getCurrentUser();
        if (!currentUser.isPresent()) return "redirect:/login";

        setComment(book, comment, currentUser.get());

        List<Comment> comments = commentService.findComentsForThisBookSortByDate(book.getId());

        model.addAttribute("comments", comments);
        model.addAttribute("user", currentUser.get());

        model.addAttribute("book", book);
        return "redirect:/books/" + book.getId();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PATCH)
    public String submitRating(@PathVariable("id") Long id,
                               @RequestParam(name = "rating")
                                       Integer rating, Model model) {

        System.out.println("1");
        Book book = bookService.findById(id).orElse(new Book());
        Optional<User> currentUser = userService.getCurrentUser();
        if (!currentUser.isPresent()) return "redirect:/login";
        System.out.println("rating" + rating);

        setRating(book, rating, currentUser.get());

        List<Comment> comments = commentService.findComentsForThisBookSortByDate(book.getId());

        model.addAttribute("comments", comments);
        model.addAttribute("book", book);
        model.addAttribute("user", currentUser.get());

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
        List<Genre> genre = genreService.findAllGenre();
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

        List<Genre> genre = genreService.findAllGenre();
        List<Book> allBooks = bookService.findAllBook();

        model.addAttribute("allBooks", allBooks);
        model.addAttribute("genres", genre);

        return "allBooks";
    }

    //todo: Migrate enumeration Genre to Entity AND Fix this method
    @RequestMapping(value = "/books/search/genre", method = RequestMethod.POST )
    public String searchByAllGenre(ModelMap model, Integer[] genreList) {
        System.out.println(Arrays.toString(genreList));

        for (int i = 0; i <genreList.length ; i++) {
            System.out.println(genreService.findAllGenre());
        }

        List<Genre> genre = genreService.findAllGenre();
        List<Book> allBooks = bookService.findAllBook();

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
        List<Genre> genre = genreService.findAllGenre();
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
        List<Genre> genre = genreService.findAllGenre();
        model.addAttribute("genres", genre);
        model.addAttribute("allBooks", allBooks);
        return "allBooks";
    }


    /**
     * The setRating() method calculates and sets up rating.
     *  @param book ;
     * @param rating ;
     * @param user
     */
    private void setRating(Book book, int rating, User user) {
        double ratingOld = book.getRating();
        int count = book.getCountRating();
        double ratingNew = (ratingOld * count + rating) / (count + 1);
        book.setRating(ratingNew);
        book.setCountRating(count + 1);
        book.addToUsersWhoSetRating(user.getId());
        bookService.updateBook(book);
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

    //ToDo: need refactoring
    @RequestMapping(value = "/books/{id}/rating/{r}", method = RequestMethod.PATCH)
    public String test(@PathVariable("id") Long id,@PathVariable("r") int rating) {
        Book book = bookService.findById(id).orElse(new Book());
        Optional<User> currentUser = userService.getCurrentUser();
        if (!currentUser.isPresent()) return "redirect:/login";

        setRating(book, rating*2, currentUser.get());
        return "greeting";
    }
}