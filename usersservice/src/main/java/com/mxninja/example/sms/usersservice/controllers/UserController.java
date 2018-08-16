package com.mxninja.example.sms.usersservice.controllers;

import com.mxninja.example.sms.usersservice.domain.User;
import com.mxninja.example.sms.usersservice.models.UserModel;
import com.mxninja.example.sms.usersservice.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

/**
 * 8/14/2018
 *
 * @author Mohammad Ali
 */

@RestController()
@RequestMapping("/users")
public class UserController {

    private final UserDao USER_DAO;

    @Autowired
    public UserController(UserDao userDao) {
        USER_DAO = userDao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/name/{name}")
    public ResponseEntity<Mono<List<User>>> findByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(Mono.just(
                USER_DAO.findAllByNameContainingIgnoreCase(name)
        ), HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Boolean>> addUser(@RequestBody UserModel body) {
        if (StringUtils.isEmpty(body.getEmail()) ||
                StringUtils.isEmpty(body.getPassword()) ||
                StringUtils.isEmpty(body.getConfirmPassword()) ||
                StringUtils.isEmpty(body.getName())) {
            return new ResponseEntity<>(Mono.just(false), HttpStatus.BAD_REQUEST);
        }

        if (!body.getPassword().equals(body.getConfirmPassword())) {
            return new ResponseEntity<>(Mono.just(false), HttpStatus.BAD_REQUEST);
        }

        if (USER_DAO.findByEmailContainingIgnoreCase(body.getEmail()) != null) {
            return new ResponseEntity<>(Mono.just(false), HttpStatus.BAD_REQUEST);
        }

        String password = body.getPassword();
        String salt = BCrypt.gensalt(12);
        String hashedPassword = BCrypt.hashpw(password, salt);

        User user = new User();
        user.setUid(UUID.randomUUID());
        user.setEmail(body.getEmail());
        user.setName(body.getName());
        user.setHashPassword(hashedPassword);
        System.out.println(USER_DAO.save(user).getUid());
        return new ResponseEntity<>(Mono.just(true), HttpStatus.CREATED);
    }
}
