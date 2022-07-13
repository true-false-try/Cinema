package com.logic.cinema.util;

import org.json.JSONObject;

public class JsonResponse {

    public static JSONObject responseMessage (String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        return jsonObject;
    }
}
