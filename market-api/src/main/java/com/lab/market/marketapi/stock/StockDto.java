package com.lab.market.marketapi.stock;

import com.lab.market.marketapi.common.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

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
