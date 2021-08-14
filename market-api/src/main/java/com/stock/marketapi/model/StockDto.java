package com.stock.marketapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    @NotEmpty
    private String id;

    @NotEmpty
    private String code;

    @NotNull
    private double price;

    @NotEmpty
    private Date createdAt;

    @NotEmpty
    private Date updatedAt;
}
