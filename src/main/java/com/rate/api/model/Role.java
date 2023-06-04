package com.rate.api.model;

public enum Role {
    STUDENT("STUDENT"),
    LECTURER("LECTURER"),
    ADMIN("ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
