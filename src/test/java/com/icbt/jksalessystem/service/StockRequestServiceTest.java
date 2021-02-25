package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.JkSalesSystemApplication;
import com.icbt.jksalessystem.model.StockRequestDTO;
import com.icbt.jksalessystem.model.request.CreateStockRequestDTO;
import com.icbt.jksalessystem.repository.BranchRepository;
import com.icbt.jksalessystem.repository.ProductRepository;
import com.icbt.jksalessystem.repository.StockRequestRepository;
import com.icbt.jksalessystem.service.impl.StockRequestServiceImpl;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = JkSalesSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockRequestServiceTest {

    private final StockRequestService stockRequestService;

    @Autowired
    public StockRequestServiceTest(StockRequestRepository stockRequestRepository, ModelMapper modelMapper,
                                   BranchRepository branchRepository, ProductRepository productRepository) {
        this.stockRequestService = new StockRequestServiceImpl(stockRequestRepository,
                modelMapper, branchRepository, productRepository);
    }

    @Test
    @Order(1)
    void createStockRequest() {
        int fromBranchId = 1;
        int toBranchId = 2;
        int productId = 1;

        CreateStockRequestDTO stockRequestDTO = new CreateStockRequestDTO(productId, fromBranchId, toBranchId);
        StockRequestDTO createdStockRequest = stockRequestService.createStockRequest(stockRequestDTO);
        assertNotNull(createdStockRequest);
        assertNotNull(createdStockRequest.getId());
    }

    @Test
    @Order(2)
    void getAllStockRequests() {
        List<StockRequestDTO> stockRequests = stockRequestService.getAllStockRequests();
        assertTrue(stockRequests.size() > 0);
    }
}