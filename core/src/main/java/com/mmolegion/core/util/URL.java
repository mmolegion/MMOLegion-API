package com.mmolegion.core.util;

import javax.servlet.http.HttpServletRequest;

public class URL {

    public static String getUrlFromRequest(HttpServletRequest request, String path) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    }
}
