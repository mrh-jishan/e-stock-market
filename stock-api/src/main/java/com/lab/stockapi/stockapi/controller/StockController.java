package com.lab.stockapi.stockapi.controller;

import com.lab.stockapi.stockapi.model.Stock;
import com.lab.stockapi.stockapi.model.StockDto;
import com.lab.stockapi.stockapi.services.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j(topic = "COMPANY_CONTROLLER")
@AllArgsConstructor
@RestController
@RequestMapping("/v1.0/market/stock")
public class StockController {

    private final StockService stockService;

    @GetMapping(value = "/get/{companyCode}/{startDate}/{endDate}",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Stock> stockList(@PathVariable(value = "companyCode") String companyCode,
                                    @PathVariable(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                    @PathVariable(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return stockService.getStockList(companyCode, startDate, endDate);
    }

    @GetMapping(value = "/get/{companyCode}",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Stock> stockList(@PathVariable(value = "companyCode") String companyCode) {
        return stockService.getStockList(companyCode);
    }

    @PostMapping(value = "/add/{companyCode}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public Stock addStock(@Valid @RequestBody StockDto stockDto,
                          @PathVariable(value = "companyCode") String companyCode) {
        return stockService.addStock(companyCode, stockDto);
    }

    @DeleteMapping(value = "/delete/{companyCode}",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Stock> deleteStock(@PathVariable(value = "companyCode") String companyCode) {
        return stockService.deleteStock(companyCode);
    }
}
