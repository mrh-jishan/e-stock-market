package com.lab.market.marketapi.mapper;

import com.lab.market.marketapi.entity.CompanyEntity;
import com.lab.market.marketapi.model.Company;
import com.lab.market.marketapi.model.CompanyDto;
import com.lab.market.marketapi.model.StockDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyEntity map(CompanyDto company);

    CompanyDto map(CompanyEntity company);

    Company map(CompanyEntity company, List<StockDto> stocks);
}
