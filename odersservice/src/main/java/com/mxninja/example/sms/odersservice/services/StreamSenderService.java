package com.mxninja.example.sms.odersservice.services;

import com.mxninja.example.sms.odersservice.config.SmsStream;
import com.mxninja.example.sms.odersservice.models.StreamMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */

@Service
@Slf4j
public class StreamSenderService {

    private final SmsStream SMS_STREAM;

    @Autowired
    public StreamSenderService(SmsStream smsStream){
        SMS_STREAM = smsStream;
    }

    public void sendGreeting(final StreamMessage greetings) {
        log.info("Sending greetings {}", greetings);
        MessageChannel messageChannel = SMS_STREAM.outboundChannel();
        messageChannel.send(MessageBuilder
                .withPayload(greetings)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
