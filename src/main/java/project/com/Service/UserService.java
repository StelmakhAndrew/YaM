package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.User;

import java.util.Optional;

/**
 *
 */
@Service
public interface UserService {
    /**
     * @param user
     */
    void createUser(User user);

    /**
     * @param user
     */
    void updateUser(User user);

    /**
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * @param id
     * @return
     */
    Optional<User> findById(Long id);

    /**
     * @param email
     * @return
     */
    boolean emailExist(String email);

    /**
     * @param login
     * @return
     */
    boolean usernameExist(String login);

    /**
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * @return
     */
    User getCurrentUser();
}
