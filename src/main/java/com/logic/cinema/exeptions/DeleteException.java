package com.logic.cinema.exeptions;

public class DeleteException extends CrudException {

    public DeleteException(String message, Long id) {
        super(String.format(message, id));

    }
}
