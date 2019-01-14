package Entity;

import jdk.jfr.Name;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "chlen")
    private Long id;


    @Column(name = "name")
    private String name;

    public User() {}

    public User(String name)
    {
        this.name = name;
    }
}
