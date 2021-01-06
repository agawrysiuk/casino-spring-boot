package com.agawrysiuk.casino.game.exception;

import com.agawrysiuk.casino.user.exception.UserDoesntExistException;
import com.agawrysiuk.casino.user.exception.UsernameExistsException;
import com.agawrysiuk.casino.user.exception.WrongCreditCardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GameControllerAdvice {

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<?> usernameExistsException(Exception ex) {
        log.info(ex.getMessage());
        return ResponseEntity.status(469).body(ex.getMessage());
    }
}
