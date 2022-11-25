package com.revature.hitthestacks.util.aspects;

import com.revature.hitthestacks.util.annotations.Authorized;
import com.revature.hitthestacks.util.exceptions.UnauthorizedException;
import com.revature.hitthestacks.util.security.TokenService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
public class AuthAspect {

    public final TokenService tokenService;

    @Autowired
    public AuthAspect(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Around("@annotation(com.revature.hitthestacks.util.annotations.Authorized)")
    public Object authenticate(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Authorized annotation = method.getAnnotation(Authorized.class);

        String token = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest()
                .getHeader("Authorization");
        System.out.println(token);
        if(!tokenService.isTokenValid(token)) throw new UnauthorizedException("No Authorization token found.");
        if(annotation.isActive() && !tokenService.extractTokenDetails(token).isActive()) throw new UnauthorizedException("Authorized Token is not an active account. Please login with an active account to perform this request");
        if(annotation.isFaculty() && !tokenService.extractTokenDetails(token).isFaculty()) throw new UnauthorizedException("Authorized Token is not a faculty account. Please login with a faculty account to perform this request");

        return pjp.proceed(pjp.getArgs());

    }
}
