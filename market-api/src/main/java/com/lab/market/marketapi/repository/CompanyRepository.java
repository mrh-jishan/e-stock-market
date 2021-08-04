package com.lab.market.marketapi.repository;

import com.lab.market.marketapi.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findByCode(String code);

    @Transactional
    Long deleteByCode(String code);
}
