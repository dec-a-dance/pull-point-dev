package com.example.pullpointdev.artist.exception;

public class NotYourArtistException extends RuntimeException{
    public NotYourArtistException(String errorMessage){
        super(errorMessage);
    }
}
