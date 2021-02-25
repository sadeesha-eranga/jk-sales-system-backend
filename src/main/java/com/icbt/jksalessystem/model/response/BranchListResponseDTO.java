package com.icbt.jksalessystem.model.response;

import com.icbt.jksalessystem.model.BranchDTO;
import lombok.*;

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
public class BranchListResponseDTO {

    private boolean success;
    private List<BranchDTO> branches;
}
