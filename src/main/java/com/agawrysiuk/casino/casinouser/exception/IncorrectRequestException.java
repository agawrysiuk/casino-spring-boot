package com.agawrysiuk.casino.casinouser.exception;

public class IncorrectRequestException extends RuntimeException {

    public IncorrectRequestException() {
        super("Request error!");
    }

    public IncorrectRequestException(String message) {
        super("Request error! " + message);
    }
}
