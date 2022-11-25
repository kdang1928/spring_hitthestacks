package com.revature.hitthestacks.util.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorized {

    boolean isFaculty() default false;
    boolean isActive() default true;

    public AuthRestriction value() default AuthRestriction.LoggedIn;
}
