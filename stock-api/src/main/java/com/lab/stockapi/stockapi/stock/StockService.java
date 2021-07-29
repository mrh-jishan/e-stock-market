package com.lab.stockapi.stockapi.stock;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository repository;
    private final StockMapper stockMapper;

    public List<StockDto> getStockList(final String companyCode,
                                       final Date startDate,
                                       final Date endDate) {
        return repository.findAll()
                .parallelStream()
                .map(stockMapper::map).collect(Collectors.toList());
    }

    public StockDto addStock(String companyCode, StockDto companyDto) {
        StockEntity companyEntity = repository.save(stockMapper.map(companyCode, companyDto));
        return stockMapper.map(companyEntity);
    }

    public List<StockDto> deleteStock(String companyCode) {
        List<StockEntity> stockEntities = repository.deleteAllByCode(companyCode);
        return stockEntities.stream().map(stockMapper::map).collect(Collectors.toList());
    }
}
