package com.icbt.jksalessystem.model;

import com.icbt.jksalessystem.enums.StockStatus;
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
public class StockRequestDTO {

    private Long id;
    private StockStatus status;
    private VehicleDTO vehicle;
    private BranchDTO fromBranch;
    private BranchDTO toBranch;
    private ProductDTO product;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
