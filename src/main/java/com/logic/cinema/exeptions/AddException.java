package com.logic.cinema.exeptions;

public class AddException extends CrudException{

    public AddException(String message, String id) {
        super(String.format(message, id));
    }
}
