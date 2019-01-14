package Repository;

import Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select b from User b where b.name = :name")
    User findByName(@Param("name") String name);

    void delete(long id);
}
