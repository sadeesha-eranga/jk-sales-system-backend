package com.icbt.jksalessystem.controller;

import com.icbt.jksalessystem.model.VehicleDTO;
import com.icbt.jksalessystem.model.request.VehicleRequestDTO;
import com.icbt.jksalessystem.model.response.CommonResponseDTO;
import com.icbt.jksalessystem.model.response.VehicleListResponseDTO;
import com.icbt.jksalessystem.service.VehicleService;
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
@RequestMapping(value = "/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO> createVehicle(@Valid @RequestBody VehicleRequestDTO reqBody) {
        log.info("createVehicle: {}", reqBody);
        vehicleService.createVehicle(reqBody);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDTO(true, "Vehicle created"));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleListResponseDTO> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(new VehicleListResponseDTO(true, vehicles));
    }

}
