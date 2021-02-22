package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.JkSalesSystemApplication;
import com.icbt.jksalessystem.model.ProductDTO;
import com.icbt.jksalessystem.model.request.ProductRequestDTO;
import com.icbt.jksalessystem.repository.ProductRepository;
import com.icbt.jksalessystem.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = JkSalesSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductServiceTest {

    private final ProductService productService;

    @Autowired
    public ProductServiceTest(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productService = new ProductServiceImpl(productRepository, modelMapper);
    }

    @Test
    void createProduct() {
        String name = "Munchee Chocolate Biscuit";
        String unit = "packets";

        ProductRequestDTO product = new ProductRequestDTO(name, unit);

        ProductDTO createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertNotNull(createdProduct.getId());
        assertEquals(name, createdProduct.getName());
        assertEquals(unit, createdProduct.getUnit());
    }

    @Test
    void getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        assertTrue(products.size() > 0);
    }
}