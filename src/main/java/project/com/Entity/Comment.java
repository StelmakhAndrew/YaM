package project.com.Entity;

import javax.persistence.*;
import java.sql.Date;


/**
 * class "comment" with properties <b>msg<b/>, <b>date<b/>
 * @autor STS
 * @version 1.1
 */
@Entity
@Table(name = "Comments")
public class Comment {

    /**
     * auto generation value for db
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    /**
     *relations with two entities User & Comment
     * @see User
     */
    @ManyToOne
    @JoinColumn
    private User user;

    /**
     *relations with two entities Book & Comment
     * @see Book
     */
    @ManyToOne
    @JoinColumn
    private Book book;

    /** field for text message */
    @Column(name = "msg")
    private String msg;

    /** field for date creations */
    @Column(name = "date")
    private Date date;


    /**
     *
     */
    public Comment() {
    }

    /**
     * @param book
     * @param msg
     */
    public Comment(Book book, String msg) {
        this.book = book;
        this.msg = msg;
    }

    public Comment(String comment) {
        this.msg = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
