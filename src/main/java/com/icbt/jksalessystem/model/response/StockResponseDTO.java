package com.icbt.jksalessystem.model.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class StockResponseDTO {

    private boolean success;
    private Long id;
    private int totalQty;
    private int remainingQty;
    private BigDecimal unitPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
