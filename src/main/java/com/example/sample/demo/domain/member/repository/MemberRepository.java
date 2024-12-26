package com.example.sample.demo.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sample.demo.domain.member.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByName(String name);
}
