package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.model.CustomerOrderDTO;
import com.icbt.jksalessystem.model.request.CustomerOrderRequestDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-23
 */
public interface CustomerOrderService {

    CustomerOrderDTO createCustomerOrder(CustomerOrderRequestDTO orderRequest);

    List<CustomerOrderDTO> searchByCustomerId(Long customerId);

    List<CustomerOrderDTO> searchByBranch(Integer branchId);
}
