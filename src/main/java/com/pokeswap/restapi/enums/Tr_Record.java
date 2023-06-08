package com.pokeswap.restapi.enums;

public enum Tr_Record {
    WALLET("Wallet"),
    TOKEN("Token");

    private final String value;

    Tr_Record(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
