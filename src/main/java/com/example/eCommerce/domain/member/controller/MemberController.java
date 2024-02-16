package com.example.eCommerce.domain.member.controller;

import com.example.eCommerce.domain.member.dto.MemberCreateDto;
import com.example.eCommerce.domain.member.service.MemberService;
import com.example.eCommerce.global.response.ResCode;
import com.example.eCommerce.global.response.ResData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResData signUp(@RequestBody @Valid MemberCreateDto memberCreateDto, Errors errors){

        memberService.signUp(memberCreateDto,errors);

        return ResData.of(ResCode.S_00_01);
    }

}
