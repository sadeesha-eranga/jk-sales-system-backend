package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.Customer;
import com.icbt.jksalessystem.exception.CustomServiceException;
import com.icbt.jksalessystem.model.CustomerDTO;
import com.icbt.jksalessystem.model.request.CustomerRequestDTO;
import com.icbt.jksalessystem.repository.CustomerRepository;
import com.icbt.jksalessystem.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.icbt.jksalessystem.util.ApplicationConstants.NotFound.CUSTOMER_NOT_FOUND;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-23
 */
@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO createCustomer(CustomerRequestDTO customerRequest) {
        Customer customer = modelMapper.map(customerRequest, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer saved: {}", savedCustomer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO searchCustomerByNic(String nic) {
        Customer customer = customerRepository.findByNic(nic)
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), CUSTOMER_NOT_FOUND));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }
}
