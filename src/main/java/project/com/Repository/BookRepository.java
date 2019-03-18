package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
