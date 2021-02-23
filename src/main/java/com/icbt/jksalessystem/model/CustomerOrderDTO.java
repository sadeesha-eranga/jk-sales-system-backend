package com.icbt.jksalessystem.model;

import com.icbt.jksalessystem.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class CustomerOrderDTO {

    private Long id;
    private BigDecimal total;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    private CustomerDTO customer;
    private BranchDTO branch;
    List<OrderDetailDTO> orderDetails;
}
