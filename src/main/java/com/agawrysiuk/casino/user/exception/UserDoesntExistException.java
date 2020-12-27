package com.agawrysiuk.casino.user.exception;

public class UserDoesntExistException extends RuntimeException {

    public UserDoesntExistException() {
        super("Error! User doesn't exist!");
    }
}
