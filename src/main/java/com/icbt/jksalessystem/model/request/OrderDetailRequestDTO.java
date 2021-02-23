package com.icbt.jksalessystem.model.request;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-23
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailRequestDTO {

    private Long stockId;
    private int qty;
    private BigDecimal amount;
}
