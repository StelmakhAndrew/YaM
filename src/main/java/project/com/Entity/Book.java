package project.com.Entity;



import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "book")
public class Book  {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn
    private User downloader;

    @Column(name = "genre")
    private Genre genre;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "image")
    private String image;

    @Column(name = "date")
    private Date date;

    @Column(name = "count_rating")
    private Integer countRating;

    @Column(name = "book")
    private String book;

    /**
     *
     */
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    /**
     *
     */
    public Book() {}

    /**
     * @param name
     * @param author
     */
    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    /**
     * @param bookDto
     */
    public Book(BookDto bookDto){
        this.name = bookDto.getName();
        this.genre = bookDto.getGenre();
        this.author = bookDto.getAuthor();
        this.description = bookDto.getDescription();
        this.image= bookDto.getImage().getOriginalFilename();
        this.date = bookDto.getDate();
        this.rating = 5.0f;
        this.countRating = 0;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComments(Comment comment){
        this.comments.add(comment);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public User getDownloader() {
        return downloader;
    }

    public void setDownloader(User downloader) {
        this.downloader = downloader;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCountRating() {
        return countRating;
    }

    public void setCountRating(int countRating) {
        this.countRating = countRating;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
