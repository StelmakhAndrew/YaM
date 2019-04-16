package project.com.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Book;
import project.com.Entity.Genre;
import project.com.Repository.BookRepository;
import project.com.Service.BookService;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class BookServiceImpl implements BookService {


    /**
     *
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * @param book
     */
    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }


    /**
     * @param book
     */
    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * @return
     */
    @Override
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    /**
     * @return
     */
    @Override
    public List<Book> findAllOrderByRating() {
        return bookRepository.findAllByOrderByRatingDesc();
    }

    /**
     * @param search
     * @return
     */
    @Override
    public List<Book> findBySearch(String search) {
        return bookRepository.findByNameContainsOrAuthorContainsOrderByRatingDesc(search,search);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * @param genre
     * @return
     */
    @Override
    public List<Book> findAllByGenre(Genre genre) {
        return bookRepository.findAllByGenre(genre);
    }

    /**
     * @param author
     * @return
     */
    @Override
    public List<Book> findAllByAuthor(String author) {
        return bookRepository.findByAuthorOrderByRatingDesc(author);
    }
}
