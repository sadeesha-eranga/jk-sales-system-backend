package com.icbt.jksalessystem.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalQty;
    private int remainingQty;
    private BigDecimal unitPrice;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Product product;

    public Stock(int totalQty, int remainingQty, BigDecimal unitPrice, Branch branch, Product product) {
        this.totalQty = totalQty;
        this.remainingQty = remainingQty;
        this.unitPrice = unitPrice;
        this.branch = branch;
        this.product = product;
    }
}
