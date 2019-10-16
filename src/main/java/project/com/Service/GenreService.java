package project.com.Service;


import org.springframework.stereotype.Service;
import project.com.Entity.Book;
import project.com.Entity.Genre;

import java.util.List;

@Service
public interface GenreService {

    List<Genre> findAllGenre();

//    void addBook(Genre genre, Book book);
}
