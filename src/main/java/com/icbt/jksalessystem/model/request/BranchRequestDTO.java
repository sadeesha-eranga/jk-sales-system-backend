package com.icbt.jksalessystem.model.request;

import com.icbt.jksalessystem.enums.BranchType;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class BranchRequestDTO {

    private Integer id;
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Please add a valid email")
    private String email;

    public BranchRequestDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
