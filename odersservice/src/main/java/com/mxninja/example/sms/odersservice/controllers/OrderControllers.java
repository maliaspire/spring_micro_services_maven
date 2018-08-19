package com.mxninja.example.sms.odersservice.controllers;

import com.mxninja.example.sms.odersservice.domain.Order;
import com.mxninja.example.sms.odersservice.models.OrderModel;
import com.mxninja.example.sms.odersservice.models.StreamMessage;
import com.mxninja.example.sms.odersservice.repository.OrderDao;
import com.mxninja.example.sms.odersservice.services.StreamSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

/**
 * 8/15/2018
 *
 * @author Mohammad Ali
 */

@RestController
@RequestMapping(value = "orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderControllers {

    private final OrderDao ORDER_DAO;
    private final StreamSenderService STREAM_SENDER_SERVICE;

    @Autowired
    public OrderControllers(OrderDao orderDao, StreamSenderService streamSenderService) {
        ORDER_DAO = orderDao;
        STREAM_SENDER_SERVICE = streamSenderService;
    }

    @GetMapping("uid/{uid}")
    public ResponseEntity<Mono<List<Order>>> findAllByUserUid(@PathVariable("uid") String uid) {
        /*TODO check user in users-service*/
        return new ResponseEntity<>(Mono.just(
                ORDER_DAO.findAllByUserUid(UUID.fromString(uid))
        ), HttpStatus.ACCEPTED);
    }

    @GetMapping("message/{message}")
    public ResponseEntity<Mono<String>> sendMessage(@PathVariable("message") String message){
        StreamMessage streamMessage = new StreamMessage();
        streamMessage.setMessage(message);
        STREAM_SENDER_SERVICE.sendGreeting(streamMessage);
        return new ResponseEntity<>(Mono.just("Done"), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<Mono<Boolean>> saveOrder(@RequestBody OrderModel body) {
        if (StringUtils.isEmpty(body.getUserUid()) || body.getPrice() == null) {
            return new ResponseEntity<>(Mono.just(false), HttpStatus.BAD_REQUEST);
        }

        /*TODO check user in users-service*/

        try{
            Order order = new Order();
            order.setUid(UUID.randomUUID());
            order.setUserUid(UUID.fromString(body.getUserUid()));
            order.setPrice(body.getPrice());
            ORDER_DAO.save(order);
            return new ResponseEntity<>(Mono.just(true), HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Mono.just(true), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
