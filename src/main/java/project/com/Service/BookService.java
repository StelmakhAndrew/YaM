package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Book;
import project.com.Entity.Genre;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public interface BookService {

    /**
     * @param book
     */
    void createBook(Book book);

    /**
     * @param book
     */
    void updateBook(Book book);

    /**
     * @return
     */
    List<Book> findAllBook();

    /**
     * @return
     */
    List<Book> findAllOrderByRating();

    /**
     * @param search
     * @return
     */
    List<Book> findBySearch(String search);

    /**
     * @param id
     * @return
     */
    Optional<Book> findById(Long id);

    /**
     * @param genre
     * @return
     */
    List<Book> findAllByGenre(Genre genre);

    /**
     * @param author
     * @return
     */
    List<Book> findAllByAuthor(String author);




}
