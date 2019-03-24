package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.com.Entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();

    List<Book> findAllByOrderByRatingAsc();

    Optional<Book> findById(Long id);

    List<Book> findByNameContainsOrAuthorContains(String name, String author);
}
