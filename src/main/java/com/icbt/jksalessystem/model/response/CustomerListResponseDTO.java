package com.icbt.jksalessystem.model.response;

import com.icbt.jksalessystem.model.CustomerDTO;
import lombok.*;

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
public class CustomerListResponseDTO {

    private boolean success;
    private List<CustomerDTO> customers;
}
