package com.icbt.jksalessystem.model.response;

import lombok.*;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommonResponseDTO {

    private boolean success;
    private String message;
}
