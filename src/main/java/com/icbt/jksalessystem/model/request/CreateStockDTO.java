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

    @Min(1)
    private int totalQty;
    @Min(1)
    private BigDecimal unitPrice;
    @NotNull(message = "Branch ID cannot be null")
    @Min(1)
    private Integer branchId;
    @NotNull(message = "Product ID cannot be null")
    @Min(1)
    private Integer productId;
}
