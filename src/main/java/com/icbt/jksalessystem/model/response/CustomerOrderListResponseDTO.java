package com.icbt.jksalessystem.model.response;

import com.icbt.jksalessystem.model.CustomerOrderDTO;
import lombok.*;

import java.util.List;

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
public class CustomerOrderListResponseDTO {

    private boolean success;
    private List<CustomerOrderDTO> customerOrders;
}
