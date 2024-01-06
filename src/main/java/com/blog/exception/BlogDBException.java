package com.blog.exception;

import lombok.Getter;

@Getter
public class BlogDBException extends RuntimeException{

    private ErrorType errorType;
    public BlogDBException(ErrorType errorType){
        super(errorType.getMessage());


        this.errorType = errorType;
    }
    public BlogDBException(ErrorType errorType, String userMessage){
        super(userMessage);
        this.errorType = errorType;
    }

}
