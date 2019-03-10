package project.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //standard getters and setters

    @Override
    public String toString() {
        return String.format("project.Entity.Person{id=%d, name='%s', age=%d}", id, name, age);
    }
}

