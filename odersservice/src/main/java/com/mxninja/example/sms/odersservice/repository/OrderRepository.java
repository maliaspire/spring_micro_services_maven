package com.mxninja.example.sms.odersservice.repository;

import com.mxninja.example.sms.odersservice.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

/**
 * 8/15/2018
 *
 * @author Mohammad Ali
 */
interface OrderRepository extends MongoRepository<Order, UUID> {

    List<Order> findAllByUserUid(UUID userUid);

}
