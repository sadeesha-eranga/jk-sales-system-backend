package com.icbt.jksalessystem.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {

    private Integer id;
    private String name;
    private String unit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<StockDTO> stocks;
}
