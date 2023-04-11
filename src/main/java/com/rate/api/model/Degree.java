package com.rate.api.model;

public enum Degree {
    PROFESSOR("професор"),
    ASSOCIATE_PROFESSOR("доцент"),
    ASSISTANT("асистент"),
    ENGINEER("інженер"),
    ASPIRANT("аспірант");

    private String value;

    Degree(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
