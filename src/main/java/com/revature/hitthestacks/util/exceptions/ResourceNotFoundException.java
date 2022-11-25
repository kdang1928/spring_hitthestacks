package com.revature.hitthestacks.util.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){}
    public ResourceNotFoundException(String s) {
        super(s);
    }
}
