package com.mmolegion.core.config;

import java.util.HashMap;

public class SystemProperties extends HashMap<String,String> {
    
    @Override
    public String get(Object name) {
        return System.getProperty(name != null ? name.toString() : "");
    }
}
