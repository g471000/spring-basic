package com.treeeye.hellospring.service;

import com.treeeye.hellospring.domain.Member;
import com.treeeye.hellospring.repository.MemberRepository;
import com.treeeye.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService service;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        Member member = new Member();
        member.setName("Jieun");

        Long savedId = service.join(member);

        Member foundMember = service.findOne(savedId).get();
        assertThat(foundMember.getName()).isEqualTo(foundMember.getName());
    }

    @Test
    void joinDuplicateMember() {
        Member member1 = new Member();
        member1.setName("Pikachu");

        Member member2 = new Member();
        member2.setName("Pikachu");

        service.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));
        assertThat(e.getMessage()).isEqualTo("Already Exists");
    }

    @Test
    void findMembers() {
        Member member1 = new Member();
        member1.setName("Pikachu");
        service.join(member1);

        Member member2 = new Member();
        member2.setName("Raichu");
        service.join(member2);

        assertThat(service.findMembers().size()).isEqualTo(2);
        assertThat(service.findMembers().contains(member1));
        assertThat(service.findMembers().contains(member2));
    }
}