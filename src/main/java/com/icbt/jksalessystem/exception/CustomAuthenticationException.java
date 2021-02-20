package com.icbt.jksalessystem.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-21
 */
@NoArgsConstructor
@Getter
@Setter
public class CustomAuthenticationException extends RuntimeException {

    private int status;
    private String message;

    public CustomAuthenticationException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
