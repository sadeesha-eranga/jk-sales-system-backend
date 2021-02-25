package com.icbt.jksalessystem.controller;

import com.icbt.jksalessystem.model.StockDTO;
import com.icbt.jksalessystem.model.request.CreateStockDTO;
import com.icbt.jksalessystem.model.response.CommonResponseDTO;
import com.icbt.jksalessystem.model.response.StockListResponseDTO;
import com.icbt.jksalessystem.service.StockService;
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
 * Date: 2021-02-22
 */
@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO> createStock(@Valid @RequestBody CreateStockDTO reqBody) {
        log.info("createStock: {}", reqBody);
        stockService.createStock(reqBody);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDTO(true, "Stock created!"));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StockListResponseDTO> getAllStocks() {
        List<StockDTO> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(new StockListResponseDTO(true, stocks));
    }

    @DeleteMapping(value = "/{stockId}")
    public ResponseEntity<CommonResponseDTO> deleteStock(@PathVariable Long stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.ok(new CommonResponseDTO(true, "Stock deleted!"));
    }
}
