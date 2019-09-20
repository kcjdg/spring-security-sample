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
    private String stockCode;
    private String stockName;
    private Double price;
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
