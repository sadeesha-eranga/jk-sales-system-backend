package com.icbt.jksalessystem.model;

import com.icbt.jksalessystem.enums.BranchStatus;
import com.icbt.jksalessystem.enums.BranchType;
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
public class BranchFullDTO {

    private Integer id;
    private String name;
    private BranchType type;
    private String email;
    private BranchStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<BranchUserDTO> users;
}
