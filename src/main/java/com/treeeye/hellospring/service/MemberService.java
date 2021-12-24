package com.treeeye.hellospring.service;

import com.treeeye.hellospring.domain.Member;
import com.treeeye.hellospring.repository.MemberRepository;
import com.treeeye.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository repository = new MemoryMemberRepository();

    public Long join (Member member) {
        validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return repository.findAll();
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
