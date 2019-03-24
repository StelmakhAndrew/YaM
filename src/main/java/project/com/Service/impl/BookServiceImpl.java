package project.com.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Book;
import project.com.Repository.BookRepository;
import project.com.Service.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllOrderByRating() {
        return bookRepository.findAllByOrderByRatingAsc();
    }

    @Override
    public List<Book> findBySearch(String search) {
        return bookRepository.findByNameContainsOrAuthorContains(search,search);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}
