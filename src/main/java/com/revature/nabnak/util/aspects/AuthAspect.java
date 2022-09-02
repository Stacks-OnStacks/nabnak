package com.revature.nabnak.util.aspects;

import com.revature.nabnak.util.exceptions.UnauthorizedException;
import com.revature.nabnak.util.web.auth.TokenService;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthAspect {

    private final TokenService tokenService;

    @Autowired
    public AuthAspect(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Order(1)
    @Before("@annotation(com.revature.nabnak.util.web.Secured)")
    public void securedEndpoint() {
        if (!sessionExists()) throw new UnauthorizedException("No session token found.");
    }

    private boolean sessionExists() {
        String token = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("Authorization");
        return tokenService.isTokenValid(token);
    }


}
