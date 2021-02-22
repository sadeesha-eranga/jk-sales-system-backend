package com.icbt.jksalessystem.service.impl;

import com.icbt.jksalessystem.entity.Driver;
import com.icbt.jksalessystem.entity.Vehicle;
import com.icbt.jksalessystem.model.VehicleDTO;
import com.icbt.jksalessystem.model.request.VehicleRequestDTO;
import com.icbt.jksalessystem.repository.DriverRepository;
import com.icbt.jksalessystem.repository.VehicleRepository;
import com.icbt.jksalessystem.service.VehicleService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-23
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Log4j2
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, DriverRepository driverRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleDTO createVehicle(VehicleRequestDTO vehicleRequest) {
        Driver driver = driverRepository.save(new Driver(vehicleRequest.getDriverName(),
                vehicleRequest.getDriverNic(), vehicleRequest.getDriverMobile()));
        Vehicle vehicle = new Vehicle(vehicleRequest.getVehicleNo(), driver);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        log.info("Vehicle saved: {}", savedVehicle);
        return modelMapper.map(savedVehicle, VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class)).collect(Collectors.toList());
    }
}
