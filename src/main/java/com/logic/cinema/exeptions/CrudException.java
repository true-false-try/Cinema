package com.logic.cinema.exeptions;

import org.json.JSONObject;

abstract class CrudException extends Exception {
    private String message;
    CrudException(String message) {
        super(message);
        this.message = message;
    }
    public String createJsonMessage(int statusCode){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", statusCode);
        jsonObject.put("message", message);
        return jsonObject.toString();
    }
}
