package com.treeeye.hellospring.service;

import com.treeeye.hellospring.domain.Member;
import com.treeeye.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

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

    @Test
    void findOne() {
        Member member1 = new Member();
        member1.setName("Meow");
        service.join(member1);

        assertThat(service.findOne(member1.getId()).get()).isEqualTo(member1);
    }
}