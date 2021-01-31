package com.myserver.myspring;

import javax.sql.DataSource;

import com.myserver.myspring.repository.JdbcMemberRepository;
import com.myserver.myspring.repository.MemberRepository;
// import com.myserver.myspring.repository.MemoryMemberRepository;
import com.myserver.myspring.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private DataSource dataSource;  //spring이 관리하는 dataSource

    @Autowired //di
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}