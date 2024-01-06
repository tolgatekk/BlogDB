package com.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    COMMENT_NOT_FOUND(5005,"Böyle bir yorum yoktur.",HttpStatus.NOT_FOUND),
    POST_NOT_FOUND(5004,"BÖYLE BİR POST BULUNAMADI.",HttpStatus.NOT_FOUND),
    INVALID_PASSWORD(5003,"Lutfen sifreyi kontrol edin.",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXSIST(5002,"Bu email adresi kullanılıyor",HttpStatus.ALREADY_REPORTED),
    CATEGORY_NOT_FOUND(5008,"Böyle bir kategori bulunamadı...", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(5001,"Böyle bir kullanıcı bulunamamıştır", HttpStatus.NOT_FOUND);


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
