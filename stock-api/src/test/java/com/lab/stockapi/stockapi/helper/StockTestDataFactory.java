package com.lab.stockapi.stockapi.helper;


import com.lab.stockapi.stockapi.model.Stock;
import com.lab.stockapi.stockapi.model.StockDto;
import com.lab.stockapi.stockapi.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Service
public class StockTestDataFactory {

    @Autowired
    private StockService stockService;

    public void createStock(String code, double price) {
        Stock stock = stockService.addStock(code, new StockDto(price));

        assertNotNull(stock.getId(), "price id must not be null!");
        assertEquals(price, stock.getPrice(), "price name updated!");
        assertEquals(code, stock.getCode(), "price name updated!");
    }
}
