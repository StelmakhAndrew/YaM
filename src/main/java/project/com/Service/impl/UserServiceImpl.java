package project.com.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.User;
import project.com.Repository.UserRepository;
import project.com.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }
}
