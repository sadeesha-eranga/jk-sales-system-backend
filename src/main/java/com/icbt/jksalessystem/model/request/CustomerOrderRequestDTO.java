package com.icbt.jksalessystem.model.request;

import com.icbt.jksalessystem.enums.PaymentMethod;
import lombok.*;

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

    private Long customerId;
    private Integer branchId;
    private List<OrderDetailRequestDTO> orderDetails;
    private BigDecimal total;
    private PaymentMethod paymentMethod;
}
