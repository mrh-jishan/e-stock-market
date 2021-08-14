package com.stock.marketapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MarketApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketApiApplication.class, args);
    }

}
