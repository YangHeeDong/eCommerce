package com.example.eCommerce.global.errors.exception;

import com.example.eCommerce.global.response.ResData;
import lombok.Getter;

@Getter
public class ApiResponseException extends RuntimeException{

    private final ResData resData;

    public ApiResponseException(ResData resData){
        this.resData = resData;
    }

}
