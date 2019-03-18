package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Book;

@Service
public interface BookService {
    void createBook(Book book);
}
