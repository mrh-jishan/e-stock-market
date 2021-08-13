package com.lab.stockapi.stockapi.config;


import com.lab.stockapi.stockapi.entity.StockEntity;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import static java.util.Optional.ofNullable;

@Configuration

public class MongoConfig {

//    @Bean
//    public AuditorAware<String> auditorProvider() {
//        return () -> {
//            StockEntity stockEntity = null;
//            return ofNullable(stockEntity).map(StockEntity::getId);
//        };
//    }
}
