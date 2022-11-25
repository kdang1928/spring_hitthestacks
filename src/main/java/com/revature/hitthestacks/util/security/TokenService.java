package com.revature.hitthestacks.util.security;

import com.revature.hitthestacks.util.exceptions.InvalidTokenException;
import com.revature.hitthestacks.util.exceptions.InvalidUserInputException;
import com.revature.hitthestacks.util.exceptions.UnauthorizedException;
import com.revature.hitthestacks.util.security.dto.Principal;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class TokenService {
    private final TokenValidator tokenValidator;
    private final TokenGenerator tokenGenerator;

    public TokenService(TokenValidator tokenValidator, TokenGenerator tokenGenerator) {
        this.tokenValidator = tokenValidator;
        this.tokenGenerator = tokenGenerator;
    }

    public String generateToken(Principal principal) {
        if(!isPrincipalValid(principal)) throw new InvalidUserInputException("Provided with invalid principal object");
        return tokenGenerator.createToken(principal);
    }

    public boolean isTokenValid(String token) {
        if(token==null || token.trim().equals("")) return false;
        return tokenValidator.parseToken(token).isPresent();
    }

    public Principal extractTokenDetails(String token) {
        if (token == null || token.trim().equals("")) throw new UnauthorizedException("No authentication token found on request");
        return tokenValidator.parseToken(token).orElseThrow(InvalidTokenException::new);
    }

    public int getDefaultTokenExpiry() {
        return tokenValidator.getDefaultTokenExpiry();
    }

    private boolean isPrincipalValid(Principal principal) {
        Predicate<String> notNullOrEmpty = (str) -> str!=null && !str.trim().equals("");
        return (principal!=null && notNullOrEmpty.test(principal.getId()) && notNullOrEmpty.test(principal.getEmail()));
    }


}
