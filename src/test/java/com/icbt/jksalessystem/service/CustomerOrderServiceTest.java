package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.JkSalesSystemApplication;
import com.icbt.jksalessystem.enums.PaymentMethod;
import com.icbt.jksalessystem.model.*;
import com.icbt.jksalessystem.model.request.*;
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
    private final CustomerService customerService;
    private final BranchService branchService;
    private final ProductService productService;
    private final StockService stockService;

    private long customerId;
    private int branchId;

    @Autowired
    public CustomerOrderServiceTest(CustomerOrderRepository customerOrderRepository,
                                    CustomerRepository customerRepository,
                                    BranchRepository branchRepository,
                                    StockRepository stockRepository,
                                    OrderDetailRepository orderDetailRepository,
                                    ModelMapper modelMapper, CustomerService customerService, BranchService branchService, ProductService productService, StockService stockService) {
        this.customerService = customerService;
        this.branchService = branchService;
        this.productService = productService;
        this.stockService = stockService;
        this.customerOrderService = new CustomerOrderServiceImpl(customerOrderRepository, customerRepository, branchRepository, stockRepository, orderDetailRepository, modelMapper);
    }

    @Test
    @Order(1)
    void createCustomerOrder() {
        CustomerRequestDTO customer = new CustomerRequestDTO("Customer Name", "123456789V", "customer1@example.com");
        CustomerDTO createdCustomer = customerService.createCustomer(customer);

        ProductRequestDTO productRequest = new ProductRequestDTO("Munchee Chocolate Biscuit", "packets");
        ProductDTO savedProduct = productService.createProduct(productRequest);

        BranchRequestDTO testBranch = new BranchRequestDTO("Branch name", "sadeeshae@ceyentra.com");
        BranchDTO savedBranch = branchService.saveBranch(testBranch);

        int productId = savedProduct.getId();
        branchId = savedBranch.getId();
        int totalQty = 100;
        BigDecimal unitPrice = new BigDecimal("100.00");

        CreateStockDTO stock = new CreateStockDTO(totalQty, unitPrice, branchId, productId);
        StockDTO createdStock = stockService.createStock(stock);

        customerId = createdCustomer.getId();
        System.out.println("cust"+ customerId);
        long stockId = createdStock.getId();
        int qty = 10;

        BigDecimal total = new BigDecimal("100.00");
        PaymentMethod paymentMethod = PaymentMethod.CASH;
        List<OrderDetailRequestDTO> orderDetails = Collections.singletonList(
                new OrderDetailRequestDTO(stockId, qty, new BigDecimal("100.00"))
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
        List<CustomerOrderDTO> orders = customerOrderService.searchByCustomerId(1L);
        System.out.println("orders "+ orders.size());
        assertTrue(orders.size() > 0);
    }

    @Test
    @Order(3)
    void searchByBranch() {
        List<CustomerOrderDTO> orders = customerOrderService.searchByBranch(2);
        assertTrue(orders.size() > 0);
    }
}
