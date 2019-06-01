package com.mmolegion.core.constants;

public enum TokenType {

    STANDARD("std"), PASSWORDRESET("password reset");

    private String type;

    public String getType() {
        return type;
    }

    TokenType(String type) {
        this.type = type;
    }

    public static TokenType fromString(String type) {
        for (TokenType t : TokenType.values()) {
            if (t.type.equalsIgnoreCase(type)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Token type is not a valid option.");
    }
}
