package com.icbt.jksalessystem.controller;

import com.icbt.jksalessystem.model.CustomerDTO;
import com.icbt.jksalessystem.model.request.CustomerRequestDTO;
import com.icbt.jksalessystem.model.response.CommonResponseDTO;
import com.icbt.jksalessystem.model.response.CustomerListResponseDTO;
import com.icbt.jksalessystem.model.response.CustomerResponseDTO;
import com.icbt.jksalessystem.service.CustomerService;
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
 * Date: 2021-02-23
 */
@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO reqBody) {
        log.info("createCustomer: {}", reqBody);
        CustomerDTO customer = customerService.createCustomer(reqBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerResponseDTO(true, customer));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerListResponseDTO> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(new CustomerListResponseDTO(true, customers));
    }

    @GetMapping(value = "/{nic}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponseDTO> searchCustomer(@PathVariable String nic) {
        log.info("searchCustomer: {}", nic);
        CustomerDTO customer = customerService.searchCustomerByNic(nic);
        return ResponseEntity.ok(new CustomerResponseDTO(true, customer));
    }
}
