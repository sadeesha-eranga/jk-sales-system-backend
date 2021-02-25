package com.icbt.jksalessystem.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
public class CreateStockDTO {

    @Min(value = 1, message = "Invalid total qty")
    private int totalQty;
    @Min(value = 1, message = "Invalid unit price")
    private BigDecimal unitPrice;
    @NotNull(message = "Branch ID cannot be null")
    @Min(value = 1, message = "Invalid branch ID")
    private Integer branchId;
    @NotNull(message = "Product ID cannot be null")
    @Min(value = 1, message = "Invalid product ID")
    private Integer productId;
}
