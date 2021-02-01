package com.myserver.myspring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.myserver.myspring.domain.Member;
import com.myserver.myspring.repository.MemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional //db 트랜잭션 롤백 자동화
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Commit
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

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
    }

}