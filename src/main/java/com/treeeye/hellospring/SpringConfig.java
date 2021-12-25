package com.treeeye.hellospring;

import com.treeeye.hellospring.repository.JpaMemberRepository;
import com.treeeye.hellospring.repository.MemberRepository;
import com.treeeye.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource ds;
    private EntityManager em;

    @Autowired
    public SpringConfig(DataSource ds, EntityManager em) {
        this.ds = ds;
        this.em = em;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }


}
