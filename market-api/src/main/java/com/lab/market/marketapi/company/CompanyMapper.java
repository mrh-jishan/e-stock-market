package com.lab.market.marketapi.company;

import com.lab.market.marketapi.stock.StockDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyEntity map(CompanyDto company);

    CompanyDto map(CompanyEntity company);

    Company map(CompanyEntity company, List<StockDto> stocks);
}
