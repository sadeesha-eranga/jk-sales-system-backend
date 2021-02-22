package com.icbt.jksalessystem.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-23
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VehicleRequestDTO {

    @NotNull(message = "Vehicle number cannot be null")
    @NotEmpty(message = "Vehicle number cannot be empty")
    private String vehicleNo;
    @NotNull(message = "Driver name cannot be null")
    @NotEmpty(message = "Driver name cannot be empty")
    private String driverName;
    @NotNull(message = "Driver NIC cannot be null")
    @NotEmpty(message = "Driver NIC cannot be empty")
    private String driverNic;
    private String driverMobile;
}
