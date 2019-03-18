package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.User;

@Service
public interface UserService {
    void createUser(User user);

}
