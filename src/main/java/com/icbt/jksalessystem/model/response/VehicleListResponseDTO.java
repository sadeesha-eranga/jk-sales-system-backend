package com.icbt.jksalessystem.model.response;

import com.icbt.jksalessystem.model.VehicleDTO;
import lombok.*;

import java.util.List;

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
public class VehicleListResponseDTO {

    private boolean success;
    private List<VehicleDTO> vehicles;
}
