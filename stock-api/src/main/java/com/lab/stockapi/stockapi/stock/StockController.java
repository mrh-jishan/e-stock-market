package com.lab.stockapi.stockapi.stock;

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
    public List<StockDto> companyInfo(@PathVariable(value = "companyCode") String companyCode,
                                      @PathVariable(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                      @PathVariable(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return stockService.getStockList(companyCode, startDate, endDate);
    }

    @PostMapping(value = "/add/{companyCode}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public StockDto registerCompany(@Valid @RequestBody StockDto stockDto,
                                    @PathVariable(value = "companyCode") String companyCode) {
        return stockService.addStock(companyCode, stockDto);
    }
}
