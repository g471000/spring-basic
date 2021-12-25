package com.treeeye.hellospring;

import com.treeeye.hellospring.repository.MemberRepository;
import com.treeeye.hellospring.repository.MemoryMemberRepository;
import com.treeeye.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }


}
