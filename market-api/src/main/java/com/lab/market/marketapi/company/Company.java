package com.lab.market.marketapi.company;

import com.lab.market.marketapi.stock.StockDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company extends CompanyDto{
    private List<StockDto> stocks;
}
