package com.myserver.myspring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.myserver.myspring.domain.Member;
import com.myserver.myspring.repository.MemoryMemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() { //dependency injection
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("loinsir");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외처리기능() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
            ()-> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 기존 회원 입니다.");
        // try {
        //     memberService.join(member2);
        //     fail();
        // } catch (IllegalStateException e) {
        //     Assertions.assertThat(e.getMessage()).isEqualTo("이미 기존 회원 입니다.123");
        // } 

        //then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}