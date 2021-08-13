package com.lab.stockapi.stockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.stockapi.stockapi.helper.StockTestDataFactory;
import com.lab.stockapi.stockapi.helper.Util;
import com.lab.stockapi.stockapi.model.Stock;
import com.lab.stockapi.stockapi.model.StockDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static com.lab.stockapi.stockapi.helper.Util.fromJson;
import static com.lab.stockapi.stockapi.helper.Util.toJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class StockControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final StockTestDataFactory stockTestDataFactory;

    @Autowired
    StockControllerTest(MockMvc mockMvc,
                        ObjectMapper objectMapper,
                        StockTestDataFactory stockTestDataFactory) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.stockTestDataFactory = stockTestDataFactory;
    }

    @Test
    void stockList() throws Exception {
        String code = String.valueOf(System.currentTimeMillis());

        stockTestDataFactory.createStock(code, 1000);
        stockTestDataFactory.createStock(code, 2000);

        MvcResult mvcResult = this.mockMvc
                .perform(get("/v1.0/market/stock/get/{companyCode}", code)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Stock> stocks = fromJson(objectMapper, mvcResult.getResponse().getContentAsString(), new TypeReference<List<Stock>>() {
        });

        assertEquals(2, stocks.size());
        assertEquals(code, stocks.get(0).getCode());
        assertEquals(2000, stocks.get(1).getPrice());
    }

    @Test
    void addStock() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(post("/v1.0/market/stock/add/{companyCode}", "xxx")
                        .content(toJson(objectMapper, new StockDto(1000)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Stock stock = fromJson(objectMapper, mvcResult.getResponse().getContentAsString(), Stock.class);
        assertNotNull(stock);
        assertEquals("xxx", stock.getCode());
        assertEquals(1000, stock.getPrice());
    }

    @Test
    void deleteStock() throws Exception {

        String code = String.valueOf(System.currentTimeMillis());

        MvcResult mvcResult = this.mockMvc
                .perform(post("/v1.0/market/stock/add/{companyCode}", code)
                        .content(toJson(objectMapper, new StockDto(1000)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Stock stock = fromJson(objectMapper, mvcResult.getResponse().getContentAsString(), Stock.class);
        assertNotNull(stock);
        assertEquals(code, stock.getCode());
        assertEquals(1000, stock.getPrice());

        MvcResult deleteResult = this.mockMvc
                .perform(delete("/v1.0/market/stock/delete/{companyCode}", code)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Stock> deletedStocks = fromJson(objectMapper, deleteResult.getResponse().getContentAsString(), new TypeReference<List<Stock>>() {
        });

        assertEquals(1, deletedStocks.size());

        MvcResult afterDeleteResult = this.mockMvc
                .perform(get("/v1.0/market/stock/get/{companyCode}", code)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Stock> stocks = fromJson(objectMapper, afterDeleteResult.getResponse().getContentAsString(), new TypeReference<List<Stock>>() {
        });

        assertEquals(Collections.emptyList(), stocks);

    }
}