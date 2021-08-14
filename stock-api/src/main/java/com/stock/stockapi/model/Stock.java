package com.stock.stockapi.model;

import com.stock.stockapi.common.DateAudit;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock extends DateAudit {

    @NotEmpty
    private String id;

    @NotEmpty
    private String code;

    @NotNull
    private double price;
}
