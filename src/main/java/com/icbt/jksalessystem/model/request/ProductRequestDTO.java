package com.icbt.jksalessystem.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class ProductRequestDTO {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Unit cannot be null")
    @NotEmpty(message = "Unit cannot be empty")
    private String unit;
}
