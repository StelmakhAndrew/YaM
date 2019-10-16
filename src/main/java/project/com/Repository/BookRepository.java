package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Book;
import project.com.Entity.Genre;

import java.util.List;
import java.util.Optional;

/**
 * A repository is a mechanism for encapsulating storage,
 * retrieval, and search behavior which emulates a collection of objects.
 *
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * method for getting all books from db
     * @return allBooks
     */
    List<Book> findAll();

    /**
     * method for getting all books order by ratingfindAllByRating
     * @return booksOrderById
     */
    List<Book> findAllByOrderByRatingDesc();

    /**
     * method for getting book by id
     * @param id;
     * @return bookById
     */
    Optional<Book> findById(Long id);


    /**
     * method for getting all books by genre
     * @param genre;
     * @return allBooksByGenre
     */


    /**
     * method for getting books by author or name and sorted by rating
     * @param name;
     * @param author;
     * @return booksByAuthorOrRating
     */
    List<Book> findByNameContainsOrAuthorContainsOrderByRatingDesc(String name, String author);

    /**
     * method for getting books by author and sorted by rating
     * @param author;
     * @return booksByAuthor
     */
    List<Book> findByAuthorOrderByRatingDesc(String author);
}
