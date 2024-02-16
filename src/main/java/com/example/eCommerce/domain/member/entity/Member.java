package com.example.eCommerce.domain.member.entity;

import com.example.eCommerce.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @NotEmpty(message = "아이디는 필수 입니다.")
    @Column(unique = true)
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String password;

    @NotEmpty(message = "이름은 필수 입니다.")
    @Column(unique = true)
    private String name;

    @NotEmpty(message = "이메일은 필수 입니다.")
    @Column(unique = true)
    private String email;

    @Builder.Default()
    private String role = "member";

    @Builder.Default()
    private String sellerRequest = "N";

}
