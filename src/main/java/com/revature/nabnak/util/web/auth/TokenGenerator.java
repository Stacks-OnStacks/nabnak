package com.revature.nabnak.util.web.auth;

import com.revature.nabnak.util.web.auth.DTO.response.Principal;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    private final JwtConfig jwtConfig;

    @Autowired
    public TokenGenerator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(Principal memberPrincipal) {

        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(memberPrincipal.getId())
                .setSubject(memberPrincipal.getEmail())
                .setIssuer("nabnak")
                .claim("isAdmin", memberPrincipal.isAdmin())
                .claim("avatar", memberPrincipal.getAvatar())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();

    }


}