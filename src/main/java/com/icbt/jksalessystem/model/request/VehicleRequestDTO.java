package com.icbt.jksalessystem.model.request;

import lombok.*;

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

    private String vehicleNo;
    private String driverName;
    private String driverNic;
    private String driverMobile;
}
