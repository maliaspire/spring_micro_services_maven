package com.mxninja.example.sms.odersservice.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

/**
 * 8/15/2018
 *
 * @author Mohammad Ali
 */

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    private final MongoDbPropertiesConfigLoader CONFIG_LOADER;

    @Autowired
    public MongoConfig(MongoDbPropertiesConfigLoader configLoader) {
        this.CONFIG_LOADER = configLoader;
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(CONFIG_LOADER.getHost());
    }

    @Override
    protected String getDatabaseName() {
        return CONFIG_LOADER.getDatabase();
    }

    @Bean
    public MongoTemplate mongoTemplate()
    {
        try{
            MappingMongoConverter converter = mappingMongoConverter();
            converter.setTypeMapper(new DefaultMongoTypeMapper(null));
            return new MongoTemplate(mongoDbFactory(), converter);
        }catch (Exception e){
            return null;
        }
    }
}
