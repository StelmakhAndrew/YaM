package project.com.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import project.com.Entity.User;
import project.com.Repository.UserRepository;
import project.com.Service.UserService;

import java.util.Optional;

/**
 * These class files are used to write business logic in a different layer
 *
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }


    @Override
    public void updateUser(User user){
        userRepository.save(user);
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user == null;
    }


    @Override
    public boolean usernameExist(String login) {
        User user = userRepository.findByUsername(login);
        return user == null;
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Object obj = auth.getPrincipal();
        String username = "";

        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }

        User u = findByUsername(username);
        return u;
    }

}
