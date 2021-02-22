package com.icbt.jksalessystem.model;

import lombok.*;

import java.time.LocalDateTime;

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
public class VehicleDTO {

    private Integer id;
    private String vehicleNo;
    private DriverDTO driver;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
