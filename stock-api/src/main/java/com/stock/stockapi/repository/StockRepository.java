package com.stock.stockapi.repository;

import com.stock.stockapi.entity.StockEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends MongoRepository<StockEntity, Long> {

    List<StockEntity> deleteAllByCode(String code);

    @Query("{'code': ?0,'createdAt' : { $gte: ?1, $lte: ?2 } }")
    List<StockEntity> findStockByQuery(String code, Date start, Date end);

    List<StockEntity> findAllByCode(String code);
}
