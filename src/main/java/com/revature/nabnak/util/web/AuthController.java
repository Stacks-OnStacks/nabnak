package com.revature.nabnak.util.web;

import com.revature.nabnak.member.Member;
import com.revature.nabnak.member.MemberService;
import com.revature.nabnak.util.web.DTO.LoginCreds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    // Dependency Injection
    @Autowired
    public AuthController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authorizeMember(@RequestBody LoginCreds loginCreds, HttpSession httpSession){
        Member authMember = memberService.login(loginCreds.getEmail(),  loginCreds.getPassword());
        httpSession.setAttribute("authMember", authMember);
    }

    @DeleteMapping
    public void logout(HttpSession httpSession){
        httpSession.invalidate();
    }

}
