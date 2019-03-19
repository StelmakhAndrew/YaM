package project.com.Entity;

import javax.persistence.*;

@Entity
@Table(name = "userser")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;



    public User() {}


    public User(UserDto userDto) {
        this.email = userDto.getEmail();
        this.name = userDto.getName();
        this.password = userDto.getPassword();
    }

    public User(String name,String email,String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
