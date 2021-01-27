package com.myserver.myspring.controller;

import com.myserver.myspring.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    
    private final MemberService memberService;

    @Autowired  //Dependency Injection (DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    
}