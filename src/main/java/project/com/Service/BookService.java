package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Book;
import project.com.Entity.Genre;
import project.com.Entity.User;

import java.util.List;
import java.util.Optional;

/**
 * These class files are used to write business logic in a different layer
 *
 */
@Service
public interface BookService {


    void createBook(Book book);

    void updateBook(Book book);

    void addToFavourite(User user, Book book);

    void removeFromFavourite(User user, Book book);

    List<Book> findAllBook();

    List<Book> findAllOrderByRating();

    List<Book> findBySearch(String search);

    Optional<Book> findById(Long id);

    List<Book> findAllByGenre(Genre genre);

    List<Book> findAllByAuthor(String author);




}
