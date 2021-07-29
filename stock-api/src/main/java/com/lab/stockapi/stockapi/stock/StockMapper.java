package com.lab.stockapi.stockapi.stock;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockEntity map(String code, StockDto stockDto);

    Stock map(StockEntity stockEntity);

    List<Stock> map(List<StockEntity> stockEntity);
}
