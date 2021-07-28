package com.lab.market.marketapi.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lab.market.marketapi.common.DateAudit;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "company")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@ToString
public class CompanyEntity extends DateAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String ceo;
    @NotNull
    private double turnover;
    @NotBlank
    private String website;
    @NotBlank
    private String exchangeCode;
}
