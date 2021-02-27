package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.model.CustomerDTO;
import com.icbt.jksalessystem.model.request.CustomerRequestDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-23
 */
public interface CustomerService {

    CustomerDTO createCustomer(CustomerRequestDTO customerRequest);

    CustomerDTO searchCustomerByNic(String nic);

    List<CustomerDTO> getAllCustomers();

    long countAll();
}
