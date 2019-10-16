package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Book;
import project.com.Entity.Genre;
import project.com.Repository.GenreRepository;
import project.com.Service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {


    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> findAllGenre() {
        return genreRepository.findAll();
    }

//    @Override
//    public void addBook(Genre genre, Book book) {
//        book.addGenreForThisBook(genre);
//        genre.addBookInThatGenre(book);
//
//    }
}
