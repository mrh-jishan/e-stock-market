package com.stock.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("company-api", r -> r.path("/api/v1.0/market/company/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://market-api:8081/"))
                .route("stock-api", r -> r.path("/api/v1.0/market/stock/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://stock-api:8082/"))
                .route("auth-api", r -> r.path("/api/v1.0/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://auth-api:8083/"))
                .route("auth-api", r -> r.path("/api/v1.0/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://auth-api:8083/"))
                .build();
    }

}
