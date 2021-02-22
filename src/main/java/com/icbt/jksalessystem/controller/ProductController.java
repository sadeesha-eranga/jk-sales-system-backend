package com.icbt.jksalessystem.controller;

import com.icbt.jksalessystem.model.ProductDTO;
import com.icbt.jksalessystem.model.request.ProductRequestDTO;
import com.icbt.jksalessystem.model.response.CommonResponseDTO;
import com.icbt.jksalessystem.model.response.ProductListResponse;
import com.icbt.jksalessystem.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO reqBody) {
        log.info("createProduct: {}", reqBody);
        productService.createProduct(reqBody);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDTO(HttpStatus.CREATED.value(), "Product created!"));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductListResponse> getAllProducts() {
        List<ProductDTO> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(new ProductListResponse(HttpStatus.OK.value(), allProducts));
    }
}
