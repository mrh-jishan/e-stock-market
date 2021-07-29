package com.lab.stockapi.stockapi.stock;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockEntity map(String code, StockDto stockDto);

    StockDto map(StockEntity stockEntity);
}
