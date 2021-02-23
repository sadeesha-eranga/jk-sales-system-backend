package com.icbt.jksalessystem.model.request;

import com.icbt.jksalessystem.enums.PaymentMethod;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

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
public class CustomerOrderRequestDTO {

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;
    @NotNull(message = "Branch ID cannot be null")
    private Integer branchId;
    @NotEmpty(message = "Order details cannot be empty")
    private List<OrderDetailRequestDTO> orderDetails;
    @Min(1)
    private BigDecimal total;
    private PaymentMethod paymentMethod;
}
