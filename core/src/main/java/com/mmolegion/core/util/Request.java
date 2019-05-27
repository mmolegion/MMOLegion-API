package com.mmolegion.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class Request {

    private static final Logger logger = LogManager.getLogger(Request.class);

    public static boolean isAuthorizationValid(HttpServletRequest request) {
        Map<String, String> headers = getHeaders(request);
        String token = headers.get("Authorization");

        if(token != null && !token.isEmpty()) {
            logger.debug("Authorization header is present. Verifying token: " + token);
            return Token.verifyToken(token);
        }

        return false;
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

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
