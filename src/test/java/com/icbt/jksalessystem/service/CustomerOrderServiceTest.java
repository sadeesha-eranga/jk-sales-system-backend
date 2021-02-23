package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.JkSalesSystemApplication;
import com.icbt.jksalessystem.enums.PaymentMethod;
import com.icbt.jksalessystem.model.CustomerOrderDTO;
import com.icbt.jksalessystem.model.request.CustomerOrderRequestDTO;
import com.icbt.jksalessystem.model.request.OrderDetailRequestDTO;
import com.icbt.jksalessystem.repository.*;
import com.icbt.jksalessystem.service.impl.CustomerOrderServiceImpl;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = JkSalesSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerOrderServiceTest {

    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderServiceTest(CustomerOrderRepository customerOrderRepository,
                                    CustomerRepository customerRepository,
                                    BranchRepository branchRepository,
                                    StockRepository stockRepository,
                                    OrderDetailRepository orderDetailRepository,
                                    ModelMapper modelMapper) {
        this.customerOrderService = new CustomerOrderServiceImpl(customerOrderRepository, customerRepository, branchRepository, stockRepository, orderDetailRepository, modelMapper);
    }

    @Test
    @Order(1)
    void createCustomerOrder() {
        long customerId = 1;
        int branchId = 1;
        BigDecimal total = new BigDecimal("100.00");
        PaymentMethod paymentMethod = PaymentMethod.CASH;
        List<OrderDetailRequestDTO> orderDetails = Collections.singletonList(
                new OrderDetailRequestDTO(1L, 10, new BigDecimal("100.00"))
        );

        CustomerOrderRequestDTO customerOrder = new CustomerOrderRequestDTO(customerId, branchId, orderDetails, total, paymentMethod);
        CustomerOrderDTO savedOrder = customerOrderService.createCustomerOrder(customerOrder);
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertEquals(savedOrder.getTotal(), total);
    }

    @Test
    @Order(2)
    void searchByCustomerId() {
        long customerId = 1;
        List<CustomerOrderDTO> orders = customerOrderService.searchByCustomerId(customerId);
        assertTrue(orders.size() > 0);
    }

    @Test
    @Order(3)
    void searchByBranch() {
        int branchId = 1;
        List<CustomerOrderDTO> orders = customerOrderService.searchByBranch(branchId);
        assertTrue(orders.size() > 0);
    }
}