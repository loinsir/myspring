package com.myserver.myspring.service;

import java.util.List;
import java.util.Optional;

import com.myserver.myspring.domain.Member;
import com.myserver.myspring.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class MemberService {
    
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        // 중복 처리
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m->{
            throw new IllegalStateException("이미 기존 회원 입니다.");
        });
    }

    /**
     * 전체 회원을 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}