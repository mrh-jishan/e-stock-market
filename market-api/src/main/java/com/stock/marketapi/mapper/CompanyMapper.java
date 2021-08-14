package com.stock.marketapi.mapper;

import com.stock.marketapi.entity.CompanyEntity;
import com.stock.marketapi.model.Company;
import com.stock.marketapi.model.CompanyDto;
import com.stock.marketapi.model.StockDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyEntity map(CompanyDto company);

    CompanyDto map(CompanyEntity company);

    Company map(CompanyEntity company, List<StockDto> stocks);
}
