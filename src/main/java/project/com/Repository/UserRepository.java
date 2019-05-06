package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.User;

import java.util.Optional;

/**
 * A repository is a mechanism for encapsulating storage,
 * retrieval, and search behavior which emulates a collection of objects.
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * method for find user by email
     * @param email;
     * @return userByEmail
     */
    User findByEmail(String email);

    /**
     * method for find user by username
     * @param username;
     * @return userByUsername
     */
    User findByUsername(String username);

    /**
     * method for find user by id
     * @param id;
     * @return userById
     */
    Optional<User> findById(Long id);
}
