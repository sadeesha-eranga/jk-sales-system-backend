package com.icbt.jksalessystem.controller;

import com.icbt.jksalessystem.model.StockRequestDTO;
import com.icbt.jksalessystem.model.request.CreateStockRequestDTO;
import com.icbt.jksalessystem.model.response.CommonResponseDTO;
import com.icbt.jksalessystem.model.response.StockRequestListResponseDTO;
import com.icbt.jksalessystem.service.StockRequestService;
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
@RequestMapping(value = "/api/v1/stocks/requests")
public class StockRequestController {

    private final StockRequestService stockRequestService;

    public StockRequestController(StockRequestService stockRequestService) {
        this.stockRequestService = stockRequestService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO> sendStockRequest(@Valid @RequestBody CreateStockRequestDTO reqBody) {
        log.info("sendStockRequest: {}", reqBody);
        stockRequestService.createStockRequest(reqBody);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDTO(true, "Stock request created!"));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StockRequestListResponseDTO> getAllStockRequests() {
        List<StockRequestDTO> stockRequests = stockRequestService.getAllStockRequests();
        return ResponseEntity.ok(new StockRequestListResponseDTO(true, stockRequests));
    }
}
