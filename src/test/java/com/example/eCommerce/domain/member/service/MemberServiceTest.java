package com.example.eCommerce.domain.member.service;

import com.example.eCommerce.domain.member.entity.Member;
import com.example.eCommerce.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class MemberServiceTest {

    @MockBean
    public MemberRepository memberRepository;

    @Autowired
    public MemberService memberService;



}
