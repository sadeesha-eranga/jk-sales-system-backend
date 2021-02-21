package com.icbt.jksalessystem.model;

import com.icbt.jksalessystem.enums.BranchType;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-21
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BranchDTO {

    private Integer id;
    private String name;
    private BranchType type;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
