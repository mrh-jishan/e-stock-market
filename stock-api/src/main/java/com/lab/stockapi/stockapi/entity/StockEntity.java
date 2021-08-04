package com.lab.stockapi.stockapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lab.stockapi.stockapi.common.DateAudit;
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
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
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
