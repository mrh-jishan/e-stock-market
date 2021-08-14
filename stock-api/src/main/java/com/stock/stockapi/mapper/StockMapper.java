package com.stock.stockapi.mapper;

import com.stock.stockapi.model.Stock;
import com.stock.stockapi.model.StockDto;
import com.stock.stockapi.entity.StockEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface StockMapper {
    StockEntity map(String code, StockDto stockDto);

    Stock map(StockEntity stockEntity);

    List<Stock> map(List<StockEntity> stockEntity);
}
