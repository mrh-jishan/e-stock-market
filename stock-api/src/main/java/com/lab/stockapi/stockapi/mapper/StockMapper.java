package com.lab.stockapi.stockapi.mapper;

import com.lab.stockapi.stockapi.model.Stock;
import com.lab.stockapi.stockapi.model.StockDto;
import com.lab.stockapi.stockapi.entity.StockEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockEntity map(String code, StockDto stockDto);

    Stock map(StockEntity stockEntity);

    List<Stock> map(List<StockEntity> stockEntity);
}
