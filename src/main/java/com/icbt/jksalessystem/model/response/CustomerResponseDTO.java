package com.icbt.jksalessystem.model.response;

import com.icbt.jksalessystem.model.CustomerDTO;
import lombok.*;

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
public class CustomerResponseDTO {

    private int code;
    private CustomerDTO customer;
}
