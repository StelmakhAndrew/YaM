package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.User;

import java.util.Optional;

/**
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * @param id
     * @return
     */
    Optional<User> findById(Long id);
}
