package com.lab.stockapi.stockapi.model;

import com.lab.stockapi.stockapi.common.DateAudit;
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
