package project.com.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Book;
import project.com.Entity.Genre;
import project.com.Entity.User;
import project.com.Repository.BookRepository;
import project.com.Service.BookService;

import java.util.List;
import java.util.Optional;

/**
 * These class files are used to write business logic in a different layer
 *
 */
@Service
public class BookServiceImpl implements BookService {


    @Autowired
    private BookRepository bookRepository;


    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }



    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void addToFavourite(User user, Book book) {

        book.addUsersWhoMArkAsFavourite(user);
        user.addToFavouriteBooks(book);

        updateBook(book);
    }

    @Override
    public void removeFromFavourite(User user, Book book) {
        user.getFavouriteBooks().remove(book);

        updateBook(book);
    }

    @Override
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllOrderByRating() {
        return bookRepository.findAllByOrderByRatingDesc();
    }

    @Override
    public List<Book> findBySearch(String search) {
        return bookRepository.findByNameContainsOrAuthorContainsOrderByRatingDesc(search,search);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }



    @Override
    public List<Book> findAllByAuthor(String author) {
        return bookRepository.findByAuthorOrderByRatingDesc(author);
    }


}
