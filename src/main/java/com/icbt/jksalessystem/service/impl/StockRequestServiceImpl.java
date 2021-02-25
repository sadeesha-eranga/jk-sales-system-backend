package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.Branch;
import com.icbt.jksalessystem.entity.Product;
import com.icbt.jksalessystem.entity.StockRequest;
import com.icbt.jksalessystem.enums.StockStatus;
import com.icbt.jksalessystem.exception.CustomServiceException;
import com.icbt.jksalessystem.model.StockRequestDTO;
import com.icbt.jksalessystem.model.request.CreateStockRequestDTO;
import com.icbt.jksalessystem.repository.BranchRepository;
import com.icbt.jksalessystem.repository.ProductRepository;
import com.icbt.jksalessystem.repository.StockRequestRepository;
import com.icbt.jksalessystem.repository.VehicleRepository;
import com.icbt.jksalessystem.service.StockRequestService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.icbt.jksalessystem.util.ApplicationConstants.NotFound.*;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-23
 */
@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StockRequestServiceImpl implements StockRequestService {

    private final StockRequestRepository stockRequestRepository;
    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public StockRequestServiceImpl(StockRequestRepository stockRequestRepository,
                                   ModelMapper modelMapper,
                                   BranchRepository branchRepository,
                                   ProductRepository productRepository) {
        this.stockRequestRepository = stockRequestRepository;
        this.modelMapper = modelMapper;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StockRequestDTO createStockRequest(CreateStockRequestDTO requestDTO) {
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), PRODUCT_NOT_FOUND));
        Branch fromBranch = branchRepository.findById(requestDTO.getFromBranchId())
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), FROM_BRANCH_NOT_FOUND));
        Branch toBranch = branchRepository.findById(requestDTO.getToBranchId())
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), TO_BRANCH_NOT_FOUND));
        StockRequest stockRequest = StockRequest.builder()
                .product(product)
                .status(StockStatus.PENDING)
                .fromBranch(fromBranch)
                .toBranch(toBranch)
                .build();
        StockRequest savedStockRequest = stockRequestRepository.save(stockRequest);
        log.info("Stock request saved: {}", savedStockRequest);
        return modelMapper.map(savedStockRequest, StockRequestDTO.class);
    }

    @Override
    public List<StockRequestDTO> getAllStockRequests() {
        return stockRequestRepository.findAll().stream()
                .map(stockRequest -> modelMapper.map(stockRequest, StockRequestDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<StockRequestDTO> getAllStockRequests(Integer branchId) {
        return stockRequestRepository.getBranchStockRequests(branchId).stream()
                .map(stockRequest -> modelMapper.map(stockRequest, StockRequestDTO.class)).collect(Collectors.toList());
    }
}
