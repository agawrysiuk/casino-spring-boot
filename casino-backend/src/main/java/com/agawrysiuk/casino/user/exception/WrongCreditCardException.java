package com.agawrysiuk.casino.user.exception;

public class WrongCreditCardException extends RuntimeException {

    public WrongCreditCardException() {
        super("Error! Card details do not match!");
    }
}
