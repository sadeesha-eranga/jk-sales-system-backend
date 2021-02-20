package com.icbt.jksalessystem.exception;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
public class CustomServiceException extends RuntimeException {

    private int code;
    private String message;

    public CustomServiceException() {
    }

    public CustomServiceException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
