package com.revature.hitthestacks.util.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
