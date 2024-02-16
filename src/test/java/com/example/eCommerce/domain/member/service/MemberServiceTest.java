package com.example.eCommerce.domain.member.service;

import com.example.eCommerce.domain.member.dto.MemberCreateDto;
import com.example.eCommerce.domain.member.repository.MemberRepository;
import com.example.eCommerce.global.errors.exception.ApiResponseException;
import com.example.eCommerce.global.response.ResCode;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.validation.*;

import java.beans.PropertyEditor;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MemberServiceTest {

    @SpyBean
    public MemberRepository memberRepository;

    @Autowired
    public MemberService memberService;


    @Transactional
    @DisplayName("회원가입 폼 검증")
    @ParameterizedTest
    @MethodSource("validForm")
    public void validMember(String loginId, String password, String passwordConfirm, String name, String email, ResCode resCode){

        // given
        MemberCreateDto memberCreateDto = MemberCreateDto.builder()
                .loginId(loginId)
                .password(password)
                .passwordConfirm(passwordConfirm)
                .email(email)
                .name(name)
                .build();

        // when
        ApiResponseException result = assertThrows(ApiResponseException.class, () -> {
//            memberService.signupValidate(memberCreateDto,);
        });

        // then
        assertThat(result.getResData().getCode()).isEqualTo(resCode);
    }

    static Stream<Arguments> validForm() {
        return Stream.of(
                Arguments.arguments("","1234","1234","홍길동","email@gmail.com", ResCode.F_01_01_01),
                Arguments.arguments("user1","","1234","홍길동","email@gmail.com", ResCode.F_01_01_01),
                Arguments.arguments("user1","1234","","홍길동","email@gmail.com", ResCode.F_01_01_01),
                Arguments.arguments("user1","1234","1234","","", ResCode.F_01_01_01),
                Arguments.arguments("user1","1234","12345","홍길동","email@gmail.com", ResCode.F_01_01_02)
        );
    }



}
