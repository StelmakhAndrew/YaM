package project.com.Entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.awt.*;

public class BookDto {

    private String name;

    private String author;

    private Genre genre;

    private String description;

    private MultipartFile image;




    public BookDto() {}

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
}
