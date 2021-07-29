package com.lab.stockapi.stockapi.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class DateAudit implements Serializable {

    @Indexed
    @CreatedDate
    private Date createdAt = new Date();

    @LastModifiedDate
    private Date updatedAt;
}
