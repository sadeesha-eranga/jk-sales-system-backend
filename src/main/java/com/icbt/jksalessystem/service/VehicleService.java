package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.model.VehicleDTO;
import com.icbt.jksalessystem.model.request.VehicleRequestDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-23
 */
public interface VehicleService {

    VehicleDTO createVehicle(VehicleRequestDTO vehicleRequest);

    List<VehicleDTO> getAllVehicles();
}
