package com.icbt.jksalessystem.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

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
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qty;
    private BigDecimal amount;

    @ManyToOne
    private CustomerOrder customerOrder;
    @ManyToOne
    private Stock stock;

    public OrderDetail(int qty, BigDecimal amount, CustomerOrder customerOrder, Stock stock) {
        this.qty = qty;
        this.amount = amount;
        this.customerOrder = customerOrder;
        this.stock = stock;
    }
}
