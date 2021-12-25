package com.treeeye.hellospring;

import com.treeeye.hellospring.aop.TimeTraceApp;
import com.treeeye.hellospring.domain.Member;
import com.treeeye.hellospring.repository.JpaMemberRepository;
import com.treeeye.hellospring.repository.MemberRepository;
import com.treeeye.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceApp timeTraceApp() {
        return new TimeTraceApp();
    }
}
