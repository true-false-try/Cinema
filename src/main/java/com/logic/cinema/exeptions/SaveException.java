package com.logic.cinema.exeptions;

public class SaveException extends CrudException{

   public SaveException(String message) {
        super(message);
    }
   public SaveException(String message, String name) {
       super(String.format(message, name));
    }
}
