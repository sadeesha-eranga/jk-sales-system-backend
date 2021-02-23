package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.*;
import com.icbt.jksalessystem.exception.CustomServiceException;
import com.icbt.jksalessystem.model.*;
import com.icbt.jksalessystem.model.request.CustomerOrderRequestDTO;
import com.icbt.jksalessystem.model.response.StockResponseDTO;
import com.icbt.jksalessystem.repository.*;
import com.icbt.jksalessystem.service.CustomerOrderService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.icbt.jksalessystem.util.ApplicationConstants.NotAvailable.QTY_NOT_AVAILABLE;
import static com.icbt.jksalessystem.util.ApplicationConstants.NotFound.*;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-23
 */
@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;
    private final CustomerRepository customerRepository;
    private final BranchRepository branchRepository;
    private final StockRepository stockRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ModelMapper modelMapper;

    public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository,
                                    CustomerRepository customerRepository,
                                    BranchRepository branchRepository,
                                    StockRepository stockRepository,
                                    OrderDetailRepository orderDetailRepository,
                                    ModelMapper modelMapper) {
        this.customerOrderRepository = customerOrderRepository;
        this.customerRepository = customerRepository;
        this.branchRepository = branchRepository;
        this.stockRepository = stockRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerOrderDTO createCustomerOrder(CustomerOrderRequestDTO orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), CUSTOMER_NOT_FOUND));
        Branch branch = branchRepository.findById(orderRequest.getBranchId())
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), BRANCH_NOT_FOUND));

        CustomerOrder customerOrder = CustomerOrder.builder()
                .customer(customer)
                .branch(branch)
                .total(orderRequest.getTotal())
                .paymentMethod(orderRequest.getPaymentMethod())
                .build();

        CustomerOrder sco = customerOrderRepository.save(customerOrder);

        List<OrderDetail> orderDetails = orderRequest.getOrderDetails()
                .stream().map(ord -> {
                    Stock stock = stockRepository.findById(ord.getStockId())
                            .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), STOCK_NOT_FOUND));
                    if (stock.getRemainingQty() < ord.getQty())
                        throw new CustomServiceException(HttpStatus.BAD_REQUEST.value(), QTY_NOT_AVAILABLE);
                    stock.setRemainingQty(stock.getRemainingQty() - ord.getQty());
                    stockRepository.save(stock);
                    return new OrderDetail(ord.getQty(), ord.getAmount(), sco, stock);
                }).collect(Collectors.toList());

        List<OrderDetail> savedOrderDetails = orderDetailRepository.saveAll(orderDetails);

        CustomerOrderDTO customerOrderDTO = modelMapper.map(sco, CustomerOrderDTO.class);
        customerOrderDTO.setOrderDetails( savedOrderDetails.stream()
                .map(od -> modelMapper.map(od, OrderDetailDTO.class)).collect(Collectors.toList()));
        return customerOrderDTO;
    }

    @Override
    public List<CustomerOrderDTO> searchByCustomerId(Long customerId) {
        return customerOrderRepository.findByCustomerId(customerId).stream()
                .map(customerOrder -> modelMapper.map(customerOrder, CustomerOrderDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerOrderDTO> searchByBranch(Integer branchId) {
        return customerOrderRepository.findByBranchId(branchId).stream()
                .map(customerOrder -> modelMapper.map(customerOrder, CustomerOrderDTO.class)).collect(Collectors.toList());
    }
}
