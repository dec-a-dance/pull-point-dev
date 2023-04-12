package com.example.pullpointdev.moderation.exception;

public class ModerationAuthException extends RuntimeException{

    public ModerationAuthException(String errorMessage){
        super(errorMessage);
    }
}
