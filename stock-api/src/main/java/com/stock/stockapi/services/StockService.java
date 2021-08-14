package com.stock.stockapi.services;

import com.stock.stockapi.mapper.StockMapper;
import com.stock.stockapi.model.Stock;
import com.stock.stockapi.model.StockDto;
import com.stock.stockapi.entity.StockEntity;
import com.stock.stockapi.repository.StockRepository;
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

    public List<Stock> getStockList(final String companyCode,
                                    final Date startDate,
                                    final Date endDate) {
        List<StockEntity> stocksByQuery = repository.findStockByQuery(companyCode, startDate, endDate);
        return stockMapper.map(stocksByQuery);
    }

    public List<Stock> getStockList(final String companyCode) {
        List<StockEntity> stocksByQuery = repository.findAllByCode(companyCode);
        return stockMapper.map(stocksByQuery);
    }

    public Stock addStock(String companyCode, StockDto companyDto) {
        StockEntity companyEntity = repository.save(stockMapper.map(companyCode, companyDto));
        return stockMapper.map(companyEntity);
    }

    public List<Stock> deleteStock(String companyCode) {
        List<StockEntity> stockEntities = repository.deleteAllByCode(companyCode);
        return stockEntities.stream().map(stockMapper::map).collect(Collectors.toList());
    }
}
