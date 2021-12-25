package com.treeeye.hellospring;

import com.treeeye.hellospring.repository.JdbcMemberRepository;
import com.treeeye.hellospring.repository.MemberRepository;
import com.treeeye.hellospring.repository.MemoryMemberRepository;
import com.treeeye.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }


}
