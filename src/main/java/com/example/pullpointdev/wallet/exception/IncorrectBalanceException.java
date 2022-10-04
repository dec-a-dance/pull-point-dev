package com.example.pullpointdev.wallet.exception;

public class IncorrectBalanceException extends RuntimeException{
    public IncorrectBalanceException(String errorMessage){
        super(errorMessage);
    }
}
