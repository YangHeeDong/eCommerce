package com.example.eCommerce.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class ResData<T> {

    private final HttpStatus status;
    private final boolean success;
    private final Object code;
    private final Object message;
    private final T data;

    public ResData(ResCode resCode,
                   T data) {
        this.status = resCode.getStatus();
        this.success = status.is2xxSuccessful();
        this.code = resCode.getCode();
        this.message = resCode.getMsg();
        this.data = data;
    }

    public static <T> ResData<T> of(ResCode resCode) {

        return ResData.of(resCode, null);
    }

    public static <T> ResData<T> of(ResCode resCode,T data) {

        return ResData.of(resCode, data);
    }

}