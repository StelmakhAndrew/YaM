package project.com.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Genre;

import java.util.List;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findAll();
}
