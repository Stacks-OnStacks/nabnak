package com.revature.nabnak.util.web.auth;

import com.revature.nabnak.util.exceptions.InvalidTokenException;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.UnauthorizedException;
import com.revature.nabnak.util.web.auth.DTO.response.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenGenerator tokenGenerator;
    private final TokenValidator tokenValidator;

    @Autowired
    public TokenService(TokenGenerator tokenGenerator, TokenValidator tokenValidator) {
        this.tokenGenerator = tokenGenerator;
        this.tokenValidator = tokenValidator;
    }

    public String generateToken(Principal subject) {
        if (!isPrincipalValid(subject)) {
            throw new InvalidUserInputException("Invalid Principal object provided!");
        }
        return tokenGenerator.createToken(subject);
    }

    public boolean isTokenValid(String token) {

        if (token == null || token.trim().equals("")) {
            return false;
        }

        return tokenValidator.parseToken(token)
                .isPresent();
    }

    public Principal extractTokenDetails(String token) {

        if (token == null || token.trim().equals("")) {
            throw new UnauthorizedException("No authentication token found on request!");
        }

        return tokenValidator.parseToken(token)
                .orElseThrow(InvalidTokenException::new);

    }

    public int getDefaultTokenExpiry() {
        return tokenValidator.getDefaultTokenExpiry();
    }

    private boolean isPrincipalValid(Principal subject) {
        return subject != null &&
                subject.getId() != null && !subject.getId().equals("") &&
                subject.getEmail() != null && !subject.getEmail().equals("");
    }

}