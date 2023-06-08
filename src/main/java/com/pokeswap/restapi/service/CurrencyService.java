package com.pokeswap.restapi.service;

import com.pokeswap.restapi.model.Currency;

import java.util.List;

public interface CurrencyService {
    Currency createCurrency(Currency currency);
    List<Currency> getAllCurrencies();

    Currency getCurrencyById(Long id);

    Currency updateCurrency(Long id, Currency currency);

    Currency deleteCurrency(Long id);

    boolean existsByName(String symbol);

    Currency getCurrencyByName(String symbol);
}