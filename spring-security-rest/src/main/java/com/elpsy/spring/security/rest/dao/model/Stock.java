package com.elpsy.spring.security.rest.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
@DynamicUpdate
@Table
@Data
@NoArgsConstructor
public class Stock implements Serializable {

    @Id
    @NotNull(message = "Stock Code cannot be null")
    private String stockCode;
    @NotNull(message = "Stock Name cannot be null")
    private String stockName;
    @DecimalMin(value = "0.05", message = "Price should at least 0.05")
    private Double price;
    @NotNull(message = "Sector cannot be null")
    private String sector;
    @CreationTimestamp
    private LocalDateTime createTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;

    public Stock(String stockCode, String stockName, Double price, String sector) {
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.price = price;
        this.sector = sector;
    }
}
