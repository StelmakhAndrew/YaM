package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.User;

import java.util.Optional;

/**
 * These class files are used to write business logic in a different layer
 *
 */
@Service
public interface UserService {

    void createUser(User user);

    void updateUser(User user);

    User findByEmail(String email);

    Optional<User> findById(Long id);

    boolean emailExist(String email);

    boolean usernameExist(String login);

    User findByUsername(String username);

    Optional<User> getCurrentUser();
}
