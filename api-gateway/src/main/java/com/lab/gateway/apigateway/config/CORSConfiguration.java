package com.lab.gateway.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class CORSConfiguration implements WebFluxConfigurer {

    private static final String ALLOWED_HEADERS = "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, x-requested-with, authorization, Content-Type, Content-Length, Authorization, credential, X-XSRF-TOKEN";
    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS, PATCH";
    private static final String ALLOWED_ORIGIN = "*";
    private static final long MAX_AGE = 7200L; //2 hours (2 * 60 * 60)

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins(ALLOWED_ORIGIN)
                .allowedHeaders(ALLOWED_HEADERS)
                .allowedMethods(ALLOWED_METHODS)
                .maxAge(MAX_AGE)
                .exposedHeaders(HttpHeaders.AUTHORIZATION,
                        HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                        HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS);
    }
}
