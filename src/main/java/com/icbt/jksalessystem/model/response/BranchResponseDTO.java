package com.icbt.jksalessystem.model.response;

import com.icbt.jksalessystem.model.BranchFullDTO;
import lombok.*;

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
public class BranchResponseDTO {

    private int code;
    private BranchFullDTO branch;
}
