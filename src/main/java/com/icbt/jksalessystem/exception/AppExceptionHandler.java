package com.icbt.jksalessystem.exception;

import com.icbt.jksalessystem.model.response.CommonResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
@Log4j2
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<CommonResponseDTO> handleInvalidInputException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CommonResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong! "));
    }

    @ExceptionHandler(value = {CustomServiceException.class})
    public ResponseEntity<Object> handleInvalidInputException(CustomServiceException ex) {
        log.error(ex.getMessage());
        return getBadRequestError(ex, ex.getCode());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<Object> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage());
        return getBadRequestError(ex, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Object> handleArgumentTypeMismatchException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        return getBadRequestError(ex, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        log.error(ex.getMessage());
        BindingResult result = ex.getBindingResult();

        List<String> errorList = result.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(HttpStatus.BAD_REQUEST.value(),
                errorList.isEmpty() ? "Invalid request data" : errorList.get(0)));
    }

    @ExceptionHandler(value = {CustomAuthenticationException.class})
    public ResponseEntity<CommonResponseDTO> handleAuthenticationException(CustomAuthenticationException ex, WebRequest webRequest) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new CommonResponseDTO(ex.getStatus(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<CommonResponseDTO> handleAccessDeniedException(AccessDeniedException ex, WebRequest webRequest) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new CommonResponseDTO(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<CommonResponseDTO> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest webRequest) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new CommonResponseDTO(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }


    private ResponseEntity<Object> getBadRequestError(Exception ex, int code) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDTO(code, ex.getMessage()));
    }
}
