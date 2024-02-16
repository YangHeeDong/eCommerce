package com.example.eCommerce.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ResCode {

    // member service success codes
    S_00_01(HttpStatus.CREATED, "S-01-01","회원가입을 완료 했습니다."),

    // member service fail codes
    F_01_01_01(HttpStatus.BAD_REQUEST, "F-01-01-01", "요청 값이 올바르지 않습니다"),
    F_01_01_02(HttpStatus.BAD_REQUEST, "F-01-01-02", "비밀번호가 서로 일치하지 않습니다"),
    F_01_01_03(HttpStatus.BAD_REQUEST, "F-01-01-03", "이미 존재하는 아이디입니다"),
    F_01_01_04(HttpStatus.BAD_REQUEST, "F-01-01-04", "이미 존재하는 이메일입니다");

    private HttpStatus status;
    private Object code;
    private Object msg;

    ResCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.msg = message;
    }

}
