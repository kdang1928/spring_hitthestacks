package com.revature.hitthestacks.util.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
