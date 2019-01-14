package Entity;

import jdk.jfr.Name;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "chlen")
    private Long id;


    @Column(name = "name")
    private String name;

    public UserEntity() {}

    public UserEntity(String name)
    {
        this.name = name;
    }
}
