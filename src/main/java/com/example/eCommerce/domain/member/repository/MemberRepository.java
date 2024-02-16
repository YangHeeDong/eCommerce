package com.example.eCommerce.domain.member.repository;

import com.example.eCommerce.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByEmail(String mail);

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);
}
