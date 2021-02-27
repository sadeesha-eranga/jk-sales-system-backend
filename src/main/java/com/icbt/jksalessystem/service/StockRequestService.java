package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.model.StockRequestDTO;
import com.icbt.jksalessystem.model.request.CreateStockRequestDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-23
 */
public interface StockRequestService {

    StockRequestDTO createStockRequest(CreateStockRequestDTO requestDTO);

    List<StockRequestDTO> getAllStockRequests();

    List<StockRequestDTO> getAllStockRequests(Integer branchId);

    long countAll(int branchId);

    List<StockRequestDTO> getSent(int branchId);

    List<StockRequestDTO> getReceived(int branchId);
}
