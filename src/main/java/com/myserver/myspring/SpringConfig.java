package com.myserver.myspring;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.myserver.myspring.repository.JdbcMemberRepository;
import com.myserver.myspring.repository.JdbcTemplateMemberRepository;
import com.myserver.myspring.repository.JpaMemberRepository;
import com.myserver.myspring.repository.MemberRepository;
// import com.myserver.myspring.repository.MemoryMemberRepository;
import com.myserver.myspring.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // private final DataSource dataSource;  //spring이 관리하는 dataSource

    // @Autowired //di
    // public SpringConfig(DataSource dataSource) {
    //     this.dataSource = dataSource;
    // }

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}