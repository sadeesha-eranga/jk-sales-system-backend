package com.icbt.jksalessystem.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class CreateStockRequestDTO {

    @Min(1)
    @NotNull(message = "Product ID cannot be null")
    private Integer productId;
    @Min(1)
    @NotNull(message = "From branch ID ID cannot be null")
    private Integer fromBranchId;
    @Min(1)
    @NotNull(message = "To branch ID cannot be null")
    private Integer toBranchId;
}
