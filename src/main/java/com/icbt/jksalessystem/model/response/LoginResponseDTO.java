package com.icbt.jksalessystem.model.response;

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
public class LoginResponseDTO {

    private boolean success;
    private String username;
    private String accessToken;
    private String role;
}
