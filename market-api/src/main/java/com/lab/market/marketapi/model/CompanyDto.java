package com.lab.market.marketapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotEmpty
    private String code;
    @NotEmpty
    private String name;
    @NotEmpty
    private String ceo;
    @NotNull
    @Min(100000000)
    private BigDecimal turnover;
    @NotEmpty
    private String website;
    @NotEmpty
    private String exchangeCode;
}
