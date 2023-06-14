package com.rate.api.model;

public enum HigherEducationDegree {
    BACHELOR("бакалавр"),
    MASTER("магістр");

    private String value;

    HigherEducationDegree(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
