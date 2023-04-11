package com.rate.api.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EducationalMethodType {
    LAB("лабораторна робота"),
    LECTURE("лекція");

    private final String value;

    EducationalMethodType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
