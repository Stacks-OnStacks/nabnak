package com.revature.nabnak.util.aspects;

import com.revature.nabnak.member.Member;
import com.revature.nabnak.util.exceptions.UnauthorizedException;
import com.revature.nabnak.util.web.Secured;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class AuthAspect {

    private final HttpServletRequest req;

    @Autowired
    public AuthAspect(HttpServletRequest req){
        this.req = req;
    }

    @Around("@annotation(com.revature.nabnak.util.web.Secured)")
    public Object securedEndpoint(ProceedingJoinPoint pjp) throws Throwable {
        // getting the signature, we know it's a MethodSignature due to our annotation Target so we can cast it and then just get the method
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Secured annotation = method.getAnnotation(Secured.class);

        List<String> allowedUsers = Arrays.asList(annotation.allowedUsers());
        HttpSession session = req.getSession(false);

        if(session == null){
            throw new UnauthorizedException("No Session available");
        }

        if(annotation.isLoggedIn() && session.getAttribute("authMember") == null){
            throw new UnauthorizedException("Please log in before calling this endpoint");
        }

        Member member = (Member) session.getAttribute("authMember");

        if(!allowedUsers.contains(member.getEmail())){
            throw new UnauthorizedException("Forbidden request made to sensitive endpoint by member: " + member.getEmail());
        }

        if(annotation.isAdmin() && member == null || !(member.getEmail() instanceof String)){
            throw new UnauthorizedException("Forbidden request made to admin endpoint by member: " + member.getEmail());
        }

        return pjp.proceed(); // all this is doing is returning that JoinPoint to execute the method following the Annotation
    }


}
