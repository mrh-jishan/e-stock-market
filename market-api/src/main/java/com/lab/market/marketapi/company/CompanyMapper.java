package com.lab.market.marketapi.company;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyEntity map(CompanyDto company);

    CompanyDto map(CompanyEntity company);
}
