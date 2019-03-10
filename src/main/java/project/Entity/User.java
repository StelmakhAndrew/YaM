package project.Entity;


import javax.persistence.*;

@Entity
@Table(name = "project.Entity.User")
public class User {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public User() {}
    public User(Long id ,String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name)
    {
        this.name = name;
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
}
