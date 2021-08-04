package com.lab.market.marketapi.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company extends CompanyDto {
    private List<StockDto> stocks;
    private double max;
    private double min;
    private double avg;
}
