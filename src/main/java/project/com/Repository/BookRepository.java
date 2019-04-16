package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.com.Entity.Book;
import project.com.Entity.Genre;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * @return
     */
    List<Book> findAll();

    /**
     * @return
     */
    List<Book> findAllByOrderByRatingDesc();

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
     * @param name
     * @param author
     * @return
     */
    List<Book> findByNameContainsOrAuthorContainsOrderByRatingDesc(String name, String author);

    /**
     * @param author
     * @return
     */
    List<Book> findByAuthorOrderByRatingDesc(String author);
}
