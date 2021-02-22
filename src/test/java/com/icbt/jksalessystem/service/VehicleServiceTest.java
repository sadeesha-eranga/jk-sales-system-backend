package com.icbt.jksalessystem.service;

import com.icbt.jksalessystem.JkSalesSystemApplication;
import com.icbt.jksalessystem.model.VehicleDTO;
import com.icbt.jksalessystem.model.request.VehicleRequestDTO;
import com.icbt.jksalessystem.repository.DriverRepository;
import com.icbt.jksalessystem.repository.VehicleRepository;
import com.icbt.jksalessystem.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = JkSalesSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VehicleServiceTest {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleServiceTest(VehicleRepository vehicleRepository, DriverRepository driverRepository, ModelMapper modelMapper) {
        this.vehicleService = new VehicleServiceImpl(vehicleRepository, driverRepository, modelMapper);
    }

    @Test
    @Order(1)
    void createVehicle() {
        String vehicleNo = "VH-1234";
        String driverName = "Driver 1";
        String driverNic = "123456789V";
        String driverMobile = "778536360";

        VehicleRequestDTO vehicle = new VehicleRequestDTO(vehicleNo, driverName, driverNic, driverMobile);
        VehicleDTO createdVehicle = vehicleService.createVehicle(vehicle);

        assertNotNull(createdVehicle);
        assertNotNull(createdVehicle.getId());
        assertEquals(createdVehicle.getVehicleNo(), vehicleNo);
    }

    @Test
    @Order(2)
    void getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        assertTrue(vehicles.size() > 0);
    }
}