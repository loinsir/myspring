package com.myserver.myspring.repository;

import java.util.Optional;

import com.myserver.myspring.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPAMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}