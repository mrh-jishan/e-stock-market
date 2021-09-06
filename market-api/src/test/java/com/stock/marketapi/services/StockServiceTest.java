package com.stock.marketapi.services;

import com.stock.marketapi.model.StockDto;
import com.stock.marketapi.util.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @InjectMocks
    private StockService stockService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void companyInfo() {
        URI uri = UriComponentsBuilder.fromUriString("http://a085b90bb46cc41589093086a8dabec3-1116436512.us-east-2.elb.amazonaws.com:8082/api/v1.0/market/stock/get/{companyCode}")
                .buildAndExpand("xxx").toUri();
        when(restTemplate.exchange(eq(uri), eq(HttpMethod.GET),
                any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<StockDto>>>any()))
                .thenReturn(ResponseEntity.ok(TestDataProvider.mockStockList()));

        List<StockDto> stocks = stockService.companyInfo("xxx");
        assertNotNull(stocks);
        assertEquals(3, stocks.size());
    }

    @Test
    void deleteStock() {
        URI uri = UriComponentsBuilder.fromUriString("http://a085b90bb46cc41589093086a8dabec3-1116436512.us-east-2.elb.amazonaws.com:8082/api/v1.0/market/stock/delete/{companyCode}")
                .buildAndExpand("xxx").toUri();
        when(restTemplate.exchange(eq(uri), eq(HttpMethod.DELETE),
                any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<StockDto>>>any()))
                .thenReturn(ResponseEntity.ok(TestDataProvider.mockStockList()));

        List<StockDto> stocks = stockService.deleteStock("xxx");
        assertNotNull(stocks);
        assertEquals(3, stocks.size());
    }
}