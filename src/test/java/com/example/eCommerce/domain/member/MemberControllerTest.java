package com.example.eCommerce.domain.member;

import com.example.eCommerce.domain.member.controller.MemberController;
import com.example.eCommerce.domain.member.dto.MemberCreateDto;
import com.example.eCommerce.global.errors.exception.ApiResponseException;
import com.example.eCommerce.global.response.ResCode;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MemberController memberController;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Transactional
    @DisplayName("회원가입 폼 검증")
    @ParameterizedTest
    @MethodSource("validForm")
    public void signup_Created(String loginId, String password, String passwordConfirm, String name, String email, ResCode resCode) throws Exception {

        // given
        MemberCreateDto memberCreateDto = MemberCreateDto.builder()
                .loginId(loginId)
                .password(password)
                .passwordConfirm(passwordConfirm)
                .email(email)
                .name(name)
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/member/signUp")
                        .content(gson.toJson(memberCreateDto))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        resultActions.andExpect(status().isBadRequest());


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
