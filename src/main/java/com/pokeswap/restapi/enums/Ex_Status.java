package com.pokeswap.restapi.enums;

public enum Ex_Status {
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private String value;

    Ex_Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

