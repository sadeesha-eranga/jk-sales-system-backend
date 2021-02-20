package com.icbt.jksalessystem.model;

import lombok.*;

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
public class LoginRequestDTO {

    private String username;
    private String password;
}
