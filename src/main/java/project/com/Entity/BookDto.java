package project.com.Entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * class BookDto to transfer date between model and view
 */
public class BookDto {

    private String name;

    private String author;

    private Genre genre;

    private String description;

    private MultipartFile image;

    private Date date;

    private MultipartFile book;

    public BookDto() {
    }

    public BookDto(String name, String author) {
        this.name = name;
        this.author = author;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MultipartFile getBook() {
        return book;
    }

    public void setBook(MultipartFile book) {
        this.book = book;
    }
}
