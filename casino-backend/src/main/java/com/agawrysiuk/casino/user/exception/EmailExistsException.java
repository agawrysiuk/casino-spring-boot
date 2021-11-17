package com.agawrysiuk.casino.user.exception;

public class EmailExistsException extends RuntimeException {

    public EmailExistsException() {
        super("Error! Email exists!");
    }
}
