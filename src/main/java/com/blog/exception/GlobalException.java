package com.blog.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(BlogDBException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(BlogDBException ex) {
        HttpStatus httpStatus = ex.getErrorType().getHttpStatus();
        return new ResponseEntity(createError(ex), httpStatus);
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(ArithmeticException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(createError(ex, httpStatus.value()), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(Exception ex) {
        System.out.println(ex.getMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(createError(ex, httpStatus.value()), httpStatus);
    }

    private ErrorMessage createError(BlogDBException ex){
        return ErrorMessage.builder()
                .code(ex.getErrorType().getCode())
                .message(ex.getMessage())
                .build();
    }
    private ErrorMessage createError(Exception ex, int value){
        return ErrorMessage.builder()
                .code(value)
                .message(ex.getMessage())
                .build();
    }
}