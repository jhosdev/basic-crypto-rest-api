package com.pokeswap.restapi.enums;

public enum Tr_Transaction {
    WITHDRAW("Withdraw"),
    DEPOSIT("Deposit"),
    EXCHANGE("Exchange");

    private final String value;

    Tr_Transaction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

