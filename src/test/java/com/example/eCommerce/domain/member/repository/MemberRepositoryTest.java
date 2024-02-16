package com.example.eCommerce.domain.member.repository;

import com.example.eCommerce.domain.member.entity.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    @DisplayName("회원 정보 저장")
    public void saveMember(){

        // given
        Member member = Member.builder()
                .loginId("user1")
                .name("홍길동")
                .password(passwordEncoder.encode("1234"))
                .email("email@email.com")
                .build();

        // when
        Member result = memberRepository.save(member);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getLoginId()).isEqualTo("user1");
        assertThat(result.getPassword()).isNotNull();
        assertThat(result.getName()).isEqualTo("홍길동");
        assertThat(result.getEmail()).isEqualTo("email@email.com");
    }

    @Test
    @Transactional
    @DisplayName("로그인 아이디로 회원 찾기")
    public void findByLoginId(){

        // given
        Member member = Member.builder()
                .loginId("user1")
                .name("홍길동")
                .password(passwordEncoder.encode("1234"))
                .email("email@email.com")
                .build();

        // when
        memberRepository.save(member);
        Member findMember = memberRepository.findByLoginId("user1").get();

        // then
        assertThat(findMember.getId()).isNotNull();
        assertThat(findMember.getLoginId()).isEqualTo("user1");
        assertThat(findMember.getPassword()).isNotNull();
        assertThat(findMember.getName()).isEqualTo("홍길동");
        assertThat(findMember.getEmail()).isEqualTo("email@email.com");
    }

    @Test
    @Transactional
    @DisplayName("이메일로 회원 찾기")
    public void findByEmail(){

        // given
        Member member = Member.builder()
                .loginId("user1")
                .name("홍길동")
                .password(passwordEncoder.encode("1234"))
                .email("email@email.com")
                .build();

        // when
        memberRepository.save(member);
        Member findMember = memberRepository.findByEmail("email@email.com").get();

        // then
        assertThat(findMember.getId()).isNotNull();
        assertThat(findMember.getLoginId()).isEqualTo("user1");
        assertThat(findMember.getPassword()).isNotNull();
        assertThat(findMember.getName()).isEqualTo("홍길동");
        assertThat(findMember.getEmail()).isEqualTo("email@email.com");
    }




}
