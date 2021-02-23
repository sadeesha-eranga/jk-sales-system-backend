package com.icbt.jksalessystem.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-24
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailDTO {

    private Long id;
    private int qty;
    private BigDecimal amount;
    private StockDTO stock;
}
