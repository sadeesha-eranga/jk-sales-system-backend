package com.icbt.jksalessystem.controller;

import com.icbt.jksalessystem.model.response.DashboardResponseDTO;
import com.icbt.jksalessystem.service.BranchService;
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

    public DashboardController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping(value = "/{branchId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DashboardResponseDTO> getDashboardDetails(@PathVariable int branchId) {
        log.info("getDashboardDetails: {}", branchId);
        long branchCount = branchService.countAll();
        return ResponseEntity.ok(new DashboardResponseDTO(true, branchCount, 0, 0, 0));
    }
}
