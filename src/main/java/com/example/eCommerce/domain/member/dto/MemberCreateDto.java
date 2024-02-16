package com.example.eCommerce.domain.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberCreateDto {

    @NotEmpty(message = "아이디는 필수 입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수 입니다.")
    private String passwordConfirm;

    @NotEmpty(message = "이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입니다.")
    private String email;
}
