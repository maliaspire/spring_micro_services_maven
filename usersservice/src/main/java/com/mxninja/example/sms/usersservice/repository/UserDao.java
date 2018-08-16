package com.mxninja.example.sms.usersservice.repository;

import com.mxninja.example.sms.usersservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 8/16/2018
 *
 * @author Mohammad Ali
 */

@Component
public class UserDao {

    private final UserRepository USER_REPOSITORY;

    @Autowired
    public UserDao(UserRepository userRepository) {
        this.USER_REPOSITORY = userRepository;
    }

    public User findByEmailContainingIgnoreCase(String email) {
        return USER_REPOSITORY.findByEmailContainingIgnoreCase(email);
    }

    public List<User> findAllByNameContainingIgnoreCase(String name) {
        return USER_REPOSITORY.findAllByNameContainingIgnoreCase(name);
    }

    public User save(User user) {
        return USER_REPOSITORY.save(user);
    }
}
