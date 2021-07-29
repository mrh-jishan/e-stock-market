package com.lab.stockapi.stockapi.stock;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<StockEntity, Long> {
    List<StockEntity> deleteAllByCode(String code);
}
