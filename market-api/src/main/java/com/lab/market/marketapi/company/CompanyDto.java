package com.lab.market.marketapi.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    private double turnover;
    @NotEmpty
    private String website;
    @NotEmpty
    private String exchangeCode;
}
