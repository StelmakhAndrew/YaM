package project.com.Entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "genre")
    private String genre;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "genresForThisBook")
    private List<Book> bookInThatGenre;



    public Genre(String genre){
        this.genre = genre;
    }

    public Genre(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Book> getBookInThatGenre() {
        return bookInThatGenre;
    }

    public void setBookInThatGenre(List<Book> bookInThatGenre) {
        this.bookInThatGenre = bookInThatGenre;
    }

//    public void addBookInThatGenre(Book book) {
//        this.bookInThatGenre.add(book);
//    }


}
