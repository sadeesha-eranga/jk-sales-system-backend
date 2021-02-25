package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.Product;
import com.icbt.jksalessystem.exception.CustomServiceException;
import com.icbt.jksalessystem.model.ProductDTO;
import com.icbt.jksalessystem.model.request.ProductRequestDTO;
import com.icbt.jksalessystem.repository.ProductRepository;
import com.icbt.jksalessystem.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.icbt.jksalessystem.util.ApplicationConstants.AlreadyExists.PRODUCT_EXISTS_WITH_NAME;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO createProduct(ProductRequestDTO productRequest) {
        if (productRepository.existsByName(productRequest.getName()))
            throw new CustomServiceException(HttpStatus.CONFLICT.value(), PRODUCT_EXISTS_WITH_NAME);

        Product savedProduct = productRepository.save(new Product(productRequest.getName(), productRequest.getUnit()));
        log.info("Product saved: {}", savedProduct);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }
}
