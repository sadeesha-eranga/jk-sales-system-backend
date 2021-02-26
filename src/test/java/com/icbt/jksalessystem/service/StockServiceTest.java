package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.JkSalesSystemApplication;
import com.icbt.jksalessystem.model.BranchDTO;
import com.icbt.jksalessystem.model.ProductDTO;
import com.icbt.jksalessystem.model.StockDTO;
import com.icbt.jksalessystem.model.request.BranchRequestDTO;
import com.icbt.jksalessystem.model.request.CreateStockDTO;
import com.icbt.jksalessystem.model.request.ProductRequestDTO;
import com.icbt.jksalessystem.repository.BranchRepository;
import com.icbt.jksalessystem.repository.ProductRepository;
import com.icbt.jksalessystem.repository.StockRepository;
import com.icbt.jksalessystem.service.impl.StockServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = JkSalesSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockServiceTest {

    private final StockService stockService;
    private final ProductService productService;
    private final BranchService branchService;

    @Autowired
    public StockServiceTest(StockRepository stockRepository,
                            BranchRepository branchRepository,
                            ProductRepository productRepository,
                            ModelMapper modelMapper,
                            ProductService productService, BranchService branchService) {
        this.productService = productService;
        this.branchService = branchService;
        this.stockService = new StockServiceImpl(stockRepository, branchRepository, productRepository, modelMapper);
    }

    @Test
    @Order(1)
    void createStock() {
        ProductRequestDTO productRequest = new ProductRequestDTO("Munchee Chocolate Biscuit", "packets");
        ProductDTO savedProduct = productService.createProduct(productRequest);

        BranchRequestDTO testBranch = new BranchRequestDTO("Branch name", "sadeeshae@ceyentra.com");
        BranchDTO savedBranch = branchService.saveBranch(testBranch);

        int productId = savedProduct.getId();
        int branchId = savedBranch.getId();
        int totalQty = 100;
        BigDecimal unitPrice = new BigDecimal("100.00");

        CreateStockDTO stock = new CreateStockDTO(totalQty, unitPrice, branchId, productId);
        StockDTO createdStock = stockService.createStock(stock);

        assertNotNull(createdStock);
        assertNotNull(createdStock.getId());
        assertEquals(createdStock.getTotalQty(), totalQty);
        assertEquals(createdStock.getUnitPrice(), unitPrice);
    }

    @Test
    @Order(2)
    void getAllStocks() {
        List<StockDTO> allStocks = stockService.getAllStocks();
        assertTrue(allStocks.size() > 0);
    }

    @Test
    @Order(3)
    void deleteStock() {
        long stockId = 1;
        boolean result = stockService.deleteStock(stockId);
        assertTrue(result);
    }
}
