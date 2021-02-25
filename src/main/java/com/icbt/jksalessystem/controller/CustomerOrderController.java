package com.icbt.jksalessystem.controller;

import com.icbt.jksalessystem.model.CustomerOrderDTO;
import com.icbt.jksalessystem.model.request.CustomerOrderRequestDTO;
import com.icbt.jksalessystem.model.response.CustomerOrderListResponseDTO;
import com.icbt.jksalessystem.model.response.CustomerOrderResponseDTO;
import com.icbt.jksalessystem.service.CustomerOrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-24
 */
@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/orders")
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerOrderResponseDTO> createCustomerOrder(@Valid @RequestBody CustomerOrderRequestDTO reqBody) {
        log.info("createCustomerOrder: {}", reqBody);
        CustomerOrderDTO customerOrder = customerOrderService.createCustomerOrder(reqBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerOrderResponseDTO(true, customerOrder));
    }

    @GetMapping(value = "/branch/{branchId}")
    public ResponseEntity<CustomerOrderListResponseDTO> getOrdersByBranch(@PathVariable int branchId) {
        List<CustomerOrderDTO> orders = customerOrderService.searchByBranch(branchId);
        return ResponseEntity.ok(new CustomerOrderListResponseDTO(true, orders));
    }

    @GetMapping(value = "/customer/{customerId}")
    public ResponseEntity<CustomerOrderListResponseDTO> getOrderByCustomer(@PathVariable Long customerId) {
        List<CustomerOrderDTO> orders = customerOrderService.searchByCustomerId(customerId);
        return ResponseEntity.ok(new CustomerOrderListResponseDTO(true, orders));
    }
}
