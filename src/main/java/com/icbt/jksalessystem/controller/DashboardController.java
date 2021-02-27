package com.icbt.jksalessystem.controller;

import com.icbt.jksalessystem.model.response.DashboardResponseDTO;
import com.icbt.jksalessystem.service.BranchService;
import com.icbt.jksalessystem.service.CustomerService;
import com.icbt.jksalessystem.service.StockRequestService;
import com.icbt.jksalessystem.service.StockService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-25
 */
@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/dashboard")
public class DashboardController {

    private final BranchService branchService;
    private final StockService stockService;
    private final CustomerService customerService;
    private final StockRequestService stockRequestService;

    public DashboardController(BranchService branchService,
                               StockService stockService,
                               CustomerService customerService,
                               StockRequestService stockRequestService) {
        this.branchService = branchService;
        this.stockService = stockService;
        this.customerService = customerService;
        this.stockRequestService = stockRequestService;
    }

    @GetMapping(value = "/{branchId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DashboardResponseDTO> getDashboardDetails(@PathVariable int branchId) {
        log.info("getDashboardDetails: {}", branchId);
        long branchCount = branchService.countAll();
        long stockRequestCount = stockRequestService.countAll(branchId);
        long customerCount = customerService.countAll();
        long stockCount = stockService.countAll(branchId);
        return ResponseEntity.ok(new DashboardResponseDTO(true, branchCount, stockRequestCount, customerCount, stockCount));
    }
}
