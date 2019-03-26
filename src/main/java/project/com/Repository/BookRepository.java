package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.com.Entity.Book;
import project.com.Entity.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();

    List<Book> findAllByOrderByRatingAsc();

    Optional<Book> findById(Long id);


    List<Book> findAllByGenre(Genre genre);

    List<Book> findByNameContainsOrAuthorContainsOrderByRatingAsc(String name, String author);
}
