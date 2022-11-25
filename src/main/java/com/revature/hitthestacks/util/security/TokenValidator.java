package com.revature.hitthestacks.util.security;

import com.revature.hitthestacks.util.exceptions.UnauthorizedException;
import com.revature.hitthestacks.util.security.dto.Principal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenValidator {
    private final JwtConfig jwtConfig;

    public TokenValidator(JwtConfig jwtConfig){
        this.jwtConfig = jwtConfig;
    }

    public Optional<Principal> parseToken(String token) {
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.of(new Principal(claims.getId(), claims.getSubject(), claims.get("isFaculty", Boolean.class), claims.get("isActive", Boolean.class)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnauthorizedException(e.getMessage());
        }
    }

    public int getDefaultTokenExpiry() {
        return jwtConfig.getExpiration();
    }
}
