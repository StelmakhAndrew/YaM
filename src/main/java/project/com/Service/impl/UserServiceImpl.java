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
 *
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     *
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * @param user
     */
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    /**
     * @param user
     */
    @Override
    public void updateUser(User user){
        userRepository.save(user);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user == null;
    }

    /**
     * @param login
     * @return
     */
    @Override
    public boolean usernameExist(String login) {
        User user = userRepository.findByUsername(login);
        return user == null;
    }

    /**
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * @return
     */
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
