package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.Branch;
import com.icbt.jksalessystem.entity.Product;
import com.icbt.jksalessystem.entity.Stock;
import com.icbt.jksalessystem.exception.CustomServiceException;
import com.icbt.jksalessystem.model.StockDTO;
import com.icbt.jksalessystem.model.request.CreateStockDTO;
import com.icbt.jksalessystem.repository.BranchRepository;
import com.icbt.jksalessystem.repository.ProductRepository;
import com.icbt.jksalessystem.repository.StockRepository;
import com.icbt.jksalessystem.service.StockService;
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
 * Date: 2021-02-22
 */
@Service
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public StockServiceImpl(StockRepository stockRepository, BranchRepository branchRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.stockRepository = stockRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StockDTO createStock(CreateStockDTO stockDTO) {
        Branch branch = branchRepository.findById(stockDTO.getBranchId())
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), BRANCH_NOT_FOUND));
        Product product = productRepository.findById(stockDTO.getProductId())
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), PRODUCT_NOT_FOUND));


        Stock stock = new Stock(stockDTO.getTotalQty(), stockDTO.getTotalQty(), stockDTO.getUnitPrice(), branch, product);
        Stock savedStock = stockRepository.save(stock);
        log.info("Stock saved: {}", savedStock);
        return modelMapper.map(savedStock, StockDTO.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteStock(Long stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new CustomServiceException(HttpStatus.NOT_FOUND.value(), STOCK_NOT_FOUND));
        stockRepository.delete(stock);
        return true;
    }

    @Override
    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll().stream()
                .map(stock -> modelMapper.map(stock, StockDTO.class)).collect(Collectors.toList());
    }
}
