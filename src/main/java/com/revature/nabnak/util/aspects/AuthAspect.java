package com.revature.nabnak.util.aspects;

import com.revature.nabnak.util.exceptions.UnauthorizedException;
import com.revature.nabnak.util.web.Secured;
import com.revature.nabnak.util.web.auth.TokenService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class AuthAspect {

    private final TokenService tokenService;

    @Autowired
    public AuthAspect(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Order(1)
    @Around("@annotation(com.revature.nabnak.util.web.Secured)")
    public Object securedEndpoint(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Secured annotation = method.getAnnotation(Secured.class);

        String token = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("Authorization");
        if (!tokenService.isTokenValid(token)) throw new UnauthorizedException("No session token found.");

        if(annotation.isAdmin() && !tokenService.extractTokenDetails(token).isAdmin()){
            throw new UnauthorizedException("Token user is not an admin. Please log in with another account or request help.");
        }

        return pjp.proceed();
    }

}
