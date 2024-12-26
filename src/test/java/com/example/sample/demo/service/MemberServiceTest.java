package com.example.sample.demo.service;

import com.example.sample.demo.domain.member.Member;
import com.example.sample.demo.domain.member.repository.MemberClassicRepository;
import com.example.sample.demo.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberClassicRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    //@Rollback(value = false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("siinBaek");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }



    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("baek");

        Member member2 = new Member();
        member2.setName("baek");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () ->
            {
                memberService.join(member2);
            }
        );
//        try {
//            memberService.join(member2); //예외가 발생해야 합니다!
//        }
//        catch (IllegalStateException e){
//            return;
//        }

        //then
        //fail("예외가 발생해야 합니다.");
    }
}