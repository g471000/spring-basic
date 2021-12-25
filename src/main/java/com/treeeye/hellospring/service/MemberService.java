package com.treeeye.hellospring.service;

import com.treeeye.hellospring.domain.Member;
import com.treeeye.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member) {
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member);
            repository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return repository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = repository.findByName(member.getName());
        result.ifPresent(i -> {
            throw new IllegalStateException("Already Exists");
        });
    }
}
