package com.example.sample.demo.service;

import com.example.sample.demo.domain.member.repository.MemberClassicRepository;
import com.example.sample.demo.domain.member.Member;
import com.example.sample.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
//    @Autowired
    private final MemberClassicRepository memberRepository;
    //@Autowired
    //private MemberClassicRepository memberClassicRepository; //필드 인젝션

//    @Autowired
//    public MemberService(MemberClassicRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     */
    @Transactional(readOnly = false)
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 전체 조회
     */
    //@Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 id로 조회
     */
    //@Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    //회원 중복 체크
    public void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName()); //중복 회원가입에 대한 최후의 방어선으로 DB에서 member의 name을 unique 조건으로 잡아놓는것이 좋다.

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
