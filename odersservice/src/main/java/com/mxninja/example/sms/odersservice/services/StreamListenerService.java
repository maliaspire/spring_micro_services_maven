package com.mxninja.example.sms.odersservice.services;

import com.mxninja.example.sms.odersservice.config.SmsStream;
import com.mxninja.example.sms.odersservice.models.StreamMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */

@Component
@Slf4j
public class StreamListenerService {

    @StreamListener(SmsStream.INPUT)
    public void handleStream(@Payload StreamMessage message) {
        System.out.println(message.getMessage());
    }

}
