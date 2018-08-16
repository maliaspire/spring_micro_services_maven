package com.mxninja.example.sms.usersservice.repository;

import com.mxninja.example.sms.usersservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 8/14/2018
 *
 * @author Mohammad Ali
 */

@Repository
interface UserRepository extends MongoRepository<User, UUID> {

    User findByEmailContainingIgnoreCase(String email);

    List<User> findAllByNameContainingIgnoreCase(String name);

}
