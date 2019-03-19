package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Book;

import java.util.List;

@Service
public interface BookService {

    void createBook(Book book);

    List<Book> findAllBook();
}
