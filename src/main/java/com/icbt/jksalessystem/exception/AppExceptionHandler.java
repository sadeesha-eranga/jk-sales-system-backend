package com.icbt.jksalessystem.exception;

import com.icbt.jksalessystem.model.CommonResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
@Log4j2
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {CustomServiceException.class})
    public ResponseEntity<Object> handleInvalidInputException(CustomServiceException ex) {
        log.error("Business Exception: " + ex.getMessage(), ex);
        return getBadRequestError(ex, ex.getCode());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<CommonResponseDTO> handleInvalidInputException(Exception ex) {
        log.error("Server Exception: " + ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CommonResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong! "));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<Object> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return getBadRequestError(ex, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Object> handleArgumentTypeMismatchException(HttpMessageNotReadableException ex) {
        return getBadRequestError(ex, HttpStatus.BAD_REQUEST.value());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Method arguments are not valid", ex);
        BindingResult result = ex.getBindingResult();

        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach(fieldError -> errorList.add(fieldError.getField() + " : " + fieldError.getDefaultMessage()
                + " : rejected value [" + fieldError.getRejectedValue() + "]"));

        result.getGlobalErrors().forEach(fieldError -> errorList.add(fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(HttpStatus.BAD_REQUEST.value(),
                errorList.isEmpty() ? "Invalid request data" : errorList.get(0)));
    }


    private ResponseEntity<Object> getBadRequestError(Exception ex, int code) {
        log.error("Bad request Exception: " + ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(code, ex.getMessage()));
    }
}
