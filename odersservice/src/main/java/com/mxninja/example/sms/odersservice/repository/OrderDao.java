package com.mxninja.example.sms.odersservice.repository;

import com.mxninja.example.sms.odersservice.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * 8/15/2018
 *
 * @author Mohammad Ali
 */

@Component
public class OrderDao {

    private final OrderRepository ORDER_REPOSITORY;

    @Autowired
    public OrderDao(OrderRepository orderRepository) {
        this.ORDER_REPOSITORY = orderRepository;
    }

    public List<Order> findAllByUserUid(UUID userUid) {
        return ORDER_REPOSITORY.findAllByUserUid(userUid);
    }

    public Order save(Order entity) {
        return ORDER_REPOSITORY.save(entity);
    }
}
