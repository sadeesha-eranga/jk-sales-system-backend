package com.icbt.jksalessystem.model.response;

import com.icbt.jksalessystem.model.CustomerOrderDTO;
import lombok.*;

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
public class CustomerOrderResponseDTO {

    private boolean success;
    private CustomerOrderDTO customerOrder;
}
