package com.mmolegion.core.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Request {

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        return processRequest(request, request.getHeaderNames(), "headers");
    }

    public static Map<String, String> getParams(HttpServletRequest request) {
        return processRequest(request, request.getParameterNames(), "params");
    }

    private static Map<String, String> processRequest(HttpServletRequest request, Enumeration enumeration, String retrieveType) {
        Map<String, String> map = new HashMap<>();

        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = null;

            if(retrieveType.equals("headers"))
                value = request.getHeader(key);
            else if(retrieveType.equals("params"))
                value = request.getParameter(key);

            map.put(key, value);
        }

        return map;
    }
}
