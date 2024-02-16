package com.example.eCommerce.domain.member.service;

import com.example.eCommerce.domain.member.dto.MemberCreateDto;
import com.example.eCommerce.domain.member.entity.Member;
import com.example.eCommerce.domain.member.repository.MemberRepository;
import com.example.eCommerce.global.errors.exception.ApiResponseException;
import com.example.eCommerce.global.response.ResCode;
import com.example.eCommerce.global.response.ResData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(@Valid MemberCreateDto formData, Errors errors) {

        this.signupValidate(formData,errors);

        Member signUp = Member.builder()
                .loginId(formData.getLoginId())
                .password(passwordEncoder.encode(formData.getPassword()))
                .email(formData.getEmail())
                .build();

        memberRepository.save(signUp);
    }

    void signupValidate(MemberCreateDto formData, Errors errors){

        if(errors.hasErrors()){
            throw new ApiResponseException(ResData.of(ResCode.F_01_01_01,"11"));
        }

        if (!formData.getPassword().equals(formData.getPasswordConfirm())) {

            errors.rejectValue("passwordConfirm", "not matched", "passwordConfirm does not matched with password");

            throw new ApiResponseException(
                    ResData.of(
                            ResCode.F_01_01_02,
                            errors
                    )
            );
        }

        if (this.memberRepository.existsByLoginId(formData.getLoginId())) {

            errors.rejectValue("loginId", "unique violation", "loginId unique violation");

            throw new ApiResponseException(
                    ResData.of(
                            ResCode.F_01_01_03,
                            errors
                    )
            );
        }

        if (this.memberRepository.existsByEmail(formData.getEmail())) {

            errors.rejectValue("email", "unique violation", "email unique violation");

            throw new ApiResponseException(
                    ResData.of(
                            ResCode.F_01_01_04,
                            errors
                    )
            );
        }
    }

}
