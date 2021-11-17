package com.agawrysiuk.casino.game.exception;

public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException() {
        super("Error! Not enough money!");
    }
}
