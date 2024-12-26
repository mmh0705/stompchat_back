package com.example.sample.demo.contorller;

import com.example.sample.demo.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.sample.demo.domain.member.Member;
import com.example.sample.demo.domain.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestApiController {
    private final MemberService memberService;

    @GetMapping(path = "/user")
    public List<MemberForm> testGetMethod(){
        List<MemberForm> memberFormList = new ArrayList<>();



        for(int i = 0; i < 10; i++){
            MemberForm memberForm = new MemberForm();

            memberForm.setId(i);
            memberForm.setName("백시인");
            memberForm.setCity("영주");
            memberForm.setStreet("123");
            memberForm.setZipcode("41414");
            memberFormList.add(memberForm);
        }

        return memberFormList;
    }
}
