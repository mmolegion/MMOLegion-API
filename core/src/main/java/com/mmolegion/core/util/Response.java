package com.mmolegion.core.util;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Response {

    public static Map<String, Object> createResponse(Map<String, String> response, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("response", response);
        map.put("status", status);

        return map;
    }
}
