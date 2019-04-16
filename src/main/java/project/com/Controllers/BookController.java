package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
 *
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
     * @param model
     * @return
     */
    @RequestMapping(value = "/bookAdd", method = RequestMethod.GET)
    public String bookAddForm(Model model) {
        List<Genre> genre = Arrays.asList(Genre.values());
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) return "redirect:/login";

        model.addAttribute("genres", genre);
        model.addAttribute("book", new BookDto());
        return "bookAdd";
    }

    /**
     * @param bookDto
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bookAdd", method = RequestMethod.POST)
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
     * @param id
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bookById", method = RequestMethod.GET)
    public String submit(@RequestParam("id") Long id, Model model) throws IOException {
        Book book = bookService.findById(id).orElse(new Book());

        List<Comment> comments = commentService.findComentsForThisBookSortByDate(book.getId());
        StringBuilder currentLineFile = new StringBuilder();
        Path path1 = Paths.get(book.getBook());
        System.out.println(book.getBook());
        System.out.println(book.getBook());
        System.out.println(book.getBook());
        System.out.println(book.getBook());
        try (BufferedReader readerFile1 = Files.newBufferedReader(path1, Charset.forName("ASCII"))) {
            int i = 0;

            while (i != 5) {

                currentLineFile.append(readerFile1.readLine());

                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        model.addAttribute("line", currentLineFile);
        model.addAttribute("comments", comments);
        model.addAttribute("book", book);
        model.addAttribute("comment", new Comment());
        return "bookById";
    }


    /**
     * @param id
     * @param comment
     * @param rating
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bookById", method = RequestMethod.POST)
    public String submitComment(@RequestParam("id") Long id,
                                @RequestParam(name = "comment", required = false, defaultValue = "") String comment,
                                @RequestParam(name = "rating", required = false, defaultValue = "0")
                                        Integer rating, Model model) throws IOException {
        Book book = bookService.findById(id).orElse(new Book());
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) return "redirect:/login";

        if (!comment.isEmpty()) {
            setComment(book, comment, currentUser);
        }
        if (rating != 0) {
            setRating(book, rating);
        }

        List<Comment> comments = commentService.findComentsForThisBookSortByDate(book.getId());

        StringBuilder currentLineFile = new StringBuilder();
        Path path1 = Paths.get(book.getBook());
        try (BufferedReader readerFile1 = Files.newBufferedReader(path1, Charset.forName("ASCII"))) {
            int i = 0;

            while (i != 5) {

                currentLineFile.append(readerFile1.readLine());

                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        model.addAttribute("line", currentLineFile);
        model.addAttribute("comments", comments);
        model.addAttribute("book", book);
        return "redirect:/bookById?id=" + book.getId();
    }

    /**
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/allbook/search", method = RequestMethod.GET)
    public String search(ModelMap model, String search) {
        List<Book> allBooks = bookService.findBySearch(search);
        List<Genre> genre = Arrays.asList(Genre.values());
        model.addAttribute("genres", genre);
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("search", search);
        return "allBooks";
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/allbook/search/genre", method = RequestMethod.GET)
    public String searchGenre(ModelMap model, Integer id) {
        List<Genre> genre = Arrays.asList(Genre.values());
        List<Book> allBooks = bookService.findAllByGenre(Genre.values()[id-1]);
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("genres", genre);
        return "allBooks";
    }

    /**
     * @param model
     * @param author
     * @return
     */
    @RequestMapping(value = "/allbook/search/author", method = RequestMethod.GET)
    public String searchAuthor(ModelMap model, String author) {
        List<Genre> genre = Arrays.asList(Genre.values());
        List<Book> allBooks = bookService.findAllByAuthor(author);
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("genres", genre);
        return "allBooks";
    }


    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/allbook", method = RequestMethod.GET)
    public String allBook(ModelMap model) {
        List<Book> allBooks = bookService.findAllOrderByRating();
        List<Genre> genre = Arrays.asList(Genre.values());
        model.addAttribute("genres", genre);
        model.addAttribute("allBooks", allBooks);
        return "allBooks";
    }

    /**
     * @param book
     * @param rating
     */
    private void setRating(Book book, int rating){
        float ratingOld = book.getRating();
        int count = book.getCountRating();
        float ratingNew = (ratingOld * count + rating) / (count + 1);
        book.setRating(ratingNew);
        book.setCountRating(count + 1);
        bookService.updateBook(book);
    }

    /**
     * @param book
     * @param comment
     * @param currentUser
     */
    private void setComment(Book book, String comment, User currentUser){
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