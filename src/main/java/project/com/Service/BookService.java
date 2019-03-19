package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Book;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    void createBook(Book book);

    List<Book> findAllBook();
    Optional<Book> findById(Long id);
}
