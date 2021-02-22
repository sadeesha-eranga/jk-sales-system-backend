package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.JkSalesSystemApplication;
import com.icbt.jksalessystem.model.StockDTO;
import com.icbt.jksalessystem.model.request.CreateStockDTO;
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

    @Autowired
    public StockServiceTest(StockRepository stockRepository, BranchRepository branchRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.stockService = new StockServiceImpl(stockRepository, branchRepository, productRepository, modelMapper);
    }

    @Test
    @Order(1)
    void createStock() {
        int productId = 1;
        int branchId = 1;
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