package com.icbt.jksalessystem.model.response;

import com.icbt.jksalessystem.model.ProductDTO;
import lombok.*;

import java.util.List;

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
public class ProductListResponse {

    private int code;
    private List<ProductDTO> products;
}
