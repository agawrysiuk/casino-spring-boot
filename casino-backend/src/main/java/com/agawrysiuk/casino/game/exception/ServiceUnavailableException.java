package com.agawrysiuk.casino.game.exception;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException() {
        super("Error! Service unavailable!");
    }
}
