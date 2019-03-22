package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.User;

import java.util.Optional;

@Service
public interface UserService {
    void createUser(User user);
    void updateUser(User user);
    User findByEmail(String email);
    Optional<User> findById(Long id);


}
