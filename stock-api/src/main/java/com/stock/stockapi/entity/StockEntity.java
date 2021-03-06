package com.stock.stockapi.entity;

import com.stock.stockapi.common.DateAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Document(collection = "Stock")
public class StockEntity extends DateAudit implements Serializable {
    @Id
    private String id;

    @NotBlank
    private String code;

    @NotNull
    private double price;
}
