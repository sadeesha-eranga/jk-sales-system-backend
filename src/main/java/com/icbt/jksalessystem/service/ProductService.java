package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.model.ProductDTO;
import com.icbt.jksalessystem.model.request.ProductRequestDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
public interface ProductService {

    ProductDTO createProduct(ProductRequestDTO productRequest);

    List<ProductDTO> getAllProducts();
}
