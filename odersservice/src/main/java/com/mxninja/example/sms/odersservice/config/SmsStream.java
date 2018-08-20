package com.mxninja.example.sms.odersservice.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */
public interface SmsStream {

    String INPUT = "sms-in";
    String OUTPUT = "sms-out";

    @Input(INPUT)
    SubscribableChannel inboundChannel();

    @Output(OUTPUT)
    MessageChannel outboundChannel();

}
