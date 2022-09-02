package com.revature.nabnak.util.web.auth;

import com.revature.nabnak.member.Member;
import com.revature.nabnak.member.MemberService;
import com.revature.nabnak.util.web.auth.DTO.request.LoginCreds;
import com.revature.nabnak.util.web.auth.DTO.response.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(exposedHeaders = "Authorization")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;
    private final TokenService tokenService;

    // Dependency Injection
    @Autowired
    public AuthController(MemberService memberService, TokenService tokenService){
        this.memberService = memberService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public Principal authorizeMember(@RequestBody LoginCreds loginCreds, HttpServletResponse resp){
        Member authMember = memberService.login(loginCreds.getEmail(), loginCreds.getPassword());
        Principal payload = new Principal(authMember);
        String token = tokenService.generateToken(payload);
        resp.setHeader("Authorization", token);
        return payload;
    }

}
