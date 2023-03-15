package com.example.pullpointdev.advice;

import com.example.pullpointdev.artist.exception.NotYourArtistException;
import com.example.pullpointdev.user.exception.FavsException;
import com.example.pullpointdev.wallet.exception.IncorrectBalanceException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointer(NullPointerException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectBalanceException.class)
    public ResponseEntity<Object> handleIncorrectBalance(IncorrectBalanceException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(FavsException.class)
    public ResponseEntity<Object> handleFavsException(IncorrectBalanceException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(NotYourArtistException.class)
    public ResponseEntity<Object> handleNotYourArtist(NotYourArtistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

}
