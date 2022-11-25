package com.revature.hitthestacks.util.security;

import com.revature.hitthestacks.util.security.dto.Principal;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {
    private final JwtConfig jwtConfig;

    public TokenGenerator(JwtConfig jwtConfig){
        this.jwtConfig = jwtConfig;
    }

    public String createToken(Principal principalUser) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(principalUser.getId())
                .setSubject(principalUser.getEmail())
                .setIssuer("HitTheStacks")
                .claim("isFaculty", principalUser.isFaculty())
                .claim("isActive", principalUser.isActive())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now+jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());
        return tokenBuilder.compact();
    }
}
