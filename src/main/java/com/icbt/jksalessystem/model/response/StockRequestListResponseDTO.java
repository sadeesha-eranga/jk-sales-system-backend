package com.icbt.jksalessystem.model.response;

import com.icbt.jksalessystem.model.StockRequestDTO;
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
public class StockRequestListResponseDTO {

    private int code;
    private List<StockRequestDTO> stockRequests;
}
