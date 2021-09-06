package com.stock.marketapi.services;

import com.stock.marketapi.model.StockDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class StockService {

    private final RestTemplate restTemplate;

    public List<StockDto> companyInfo(String companyCode) {
        URI uri = UriComponentsBuilder.fromUriString("http://stock-api:8082/api/v1.0/market/stock/get/{companyCode}")
                .buildAndExpand(companyCode).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<StockDto>> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<StockDto>>() {
        });
        return response.getBody();
    }

    public List<StockDto> deleteStock(String companyCode) {
        URI uri = UriComponentsBuilder.fromUriString("http://stock-api:8082/api/v1.0/market/stock/delete/{companyCode}")
                .buildAndExpand(companyCode).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<StockDto>> response = restTemplate.exchange(uri, HttpMethod.DELETE, entity, new ParameterizedTypeReference<List<StockDto>>() {
        });
        return response.getBody();
    }

}
