package com.agawrysiuk.casino.user.exception;

public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException() {
        super("Error! Username exists!");
    }
}
