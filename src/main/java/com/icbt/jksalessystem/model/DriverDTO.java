package com.icbt.jksalessystem.model;

import lombok.*;

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
public class DriverDTO {

    private Integer id;
    private String name;
    private String nic;
    private String mobile;
}
