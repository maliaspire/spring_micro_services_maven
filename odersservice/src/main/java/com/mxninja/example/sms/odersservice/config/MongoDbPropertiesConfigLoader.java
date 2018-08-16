package com.mxninja.example.sms.odersservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 8/15/2018
 *
 * @author Mohammad Ali
 * <p>
 * Entity class to handle mongoDb Properties
 */
@Data
@Validated
@ConfigurationProperties("sms.service.orders.mongodb")
@Component
public class MongoDbPropertiesConfigLoader {

    private String host;

    private String database;


}






