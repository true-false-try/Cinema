package com.logic.cinema.exeptions;

abstract class CrudException extends Exception {
    CrudException(String message) {
        super(message);
    }
}
