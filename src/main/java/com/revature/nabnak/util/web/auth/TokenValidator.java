package com.revature.nabnak.util.web.auth;

import com.revature.nabnak.util.web.auth.DTO.response.Principal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenValidator {

    private final JwtConfig jwtConfig;

    @Autowired
    public TokenValidator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public Optional<Principal> parseToken(String token) {

        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println(claims);
            return Optional.of(new Principal(claims.getId(), claims.getSubject(), claims.get("isAdmin", Boolean.class)));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage()); // TODO replace with something better
        }

    }

    public int getDefaultTokenExpiry() {
        return jwtConfig.getExpiration();
    }

}