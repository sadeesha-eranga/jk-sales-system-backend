package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.model.StockDTO;
import com.icbt.jksalessystem.model.request.CreateStockDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */

public interface StockService {

    StockDTO createStock(CreateStockDTO stockDTO);

    boolean deleteStock(Long stockId);

    List<StockDTO> getAllStocks();

    List<StockDTO> getAllStocks(Integer branchId);

    long countAll(int branchId);
}
